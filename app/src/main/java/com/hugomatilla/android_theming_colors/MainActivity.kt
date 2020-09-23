package com.hugomatilla.android_theming_colors

import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.hugomatilla.android_theming_colors.R.id
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import kotlinx.android.synthetic.main.activity_main.fragment_container

class MainActivity : AppCompatActivity() {

  private lateinit var simpleFragment: SimpleFragment
  private lateinit var overlayFragment: OverlayFragment
  private lateinit var widgetsFragment: WidgetsFragment
  private lateinit var paletteFragment: PaletteFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupFragments()
    setupBottomNavigation()
//    addDarkModeButton()
  }

  private fun addDarkModeButton() {
    val darkButton =
      Button(this).apply {
        text = "Dark Mode Toggle"
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
      }
    fragment_container.addView(darkButton)

    darkButton.setOnClickListener {
      val goDark = getDefaultNightMode() != MODE_NIGHT_YES
      AppCompatDelegate.setDefaultNightMode(if (goDark) MODE_NIGHT_YES else MODE_NIGHT_NO)
    }
  }

  private fun setupFragments() {
    simpleFragment = SimpleFragment()
    overlayFragment = OverlayFragment()
    widgetsFragment = WidgetsFragment()
    paletteFragment = PaletteFragment()

    supportFragmentManager.commit {
      add(R.id.fragment_container, overlayFragment, null)
      add(R.id.fragment_container, widgetsFragment, null)
      add(R.id.fragment_container, paletteFragment, null)
      add(R.id.fragment_container, simpleFragment, null)
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
    }
  }

  private fun Fragment.show() {
    val fragment = this
    supportFragmentManager.commit { replace(R.id.fragment_container, fragment, null) }
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

  companion object {
    private const val LAST_SELECTED_ITEM = "LAST_SELECTED_ITEM"
  }

}
