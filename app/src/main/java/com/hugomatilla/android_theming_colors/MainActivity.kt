package com.hugomatilla.android_theming_colors

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.hugomatilla.android_theming_colors.R.id
import kotlinx.android.synthetic.main.activity_main.bottomNavigation

class MainActivity : AppCompatActivity() {

  private lateinit var simpleFragment: SimpleFragment
  private lateinit var overlayFragment: OverlayFragment
  private lateinit var widgetsFragment: WidgetsFragment
  private lateinit var paletteFragment: PaletteFragment
  private lateinit var alternativeThemeFragment: ThemeAlternativeFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setTheme(R.style.Theme_MyApp_Alternative) // uncomment to change the activity's theme
    setContentView(R.layout.activity_main)
    setupFragments()
    setupBottomNavigation()

  }

  private fun setupFragments() {
    simpleFragment = SimpleFragment()
    overlayFragment = OverlayFragment()
    widgetsFragment = WidgetsFragment()
    paletteFragment = PaletteFragment()
    alternativeThemeFragment = ThemeAlternativeFragment()

    supportFragmentManager.commit {
      add(R.id.fragmentContainer, alternativeThemeFragment, null)
      add(R.id.fragmentContainer, overlayFragment, null)
      add(R.id.fragmentContainer, widgetsFragment, null)
      add(R.id.fragmentContainer, paletteFragment, null)
      add(R.id.fragmentContainer, simpleFragment, null)
    }

    simpleFragment.show()
  }

  private fun setupBottomNavigation() {
    bottomNavigation.setOnNavigationItemSelectedListener {
      showFragmentByMenuItemId(it.itemId)
      true
    }
  }

  private fun showFragmentByMenuItemId(itemId: Int) {
    when (itemId) {
      id.simple -> simpleFragment.show()
      id.palette -> paletteFragment.show()
      id.widgets -> widgetsFragment.show()
      id.overlay -> overlayFragment.show()
      id.alternative -> alternativeThemeFragment.show()
    }
  }

  private fun Fragment.show() {
    val fragment = this
    supportFragmentManager.commit { replace(R.id.fragmentContainer, fragment, null) }
  }

  override fun onSaveInstanceState(savedInstanceState: Bundle) {
    super.onSaveInstanceState(savedInstanceState)
    savedInstanceState.putInt(LAST_SELECTED_ITEM, bottomNavigation.selectedItemId)
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    val itemId = savedInstanceState.getInt(LAST_SELECTED_ITEM, R.id.simple)
    showFragmentByMenuItemId(itemId)
    bottomNavigation.selectedItemId = itemId
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
    id.darkToggle -> {
      val goDark = getDefaultNightMode() != MODE_NIGHT_YES
      AppCompatDelegate.setDefaultNightMode(if (goDark) MODE_NIGHT_YES else MODE_NIGHT_NO)
      true
    }
    else -> {
      super.onOptionsItemSelected(item)
    }
  }

  companion object {
    private const val LAST_SELECTED_ITEM = "LAST_SELECTED_ITEM"
  }
}
