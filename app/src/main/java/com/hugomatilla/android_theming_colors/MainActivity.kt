package com.hugomatilla.android_theming_colors

import android.R.id
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.bottomNavigation

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
    addDarkModeButton()
  }

  private fun addDarkModeButton() {
    val darkButton =
      Button(this).apply {
        text = "Dark Mode Toggle"
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
      }
    val rootView = (findViewById<View>(id.content) as ViewGroup).getChildAt(0) as ViewGroup
    rootView.addView(darkButton)

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
      when (it.itemId) {
        R.id.simple -> simpleFragment.show()
        R.id.palette -> paletteFragment.show()
        R.id.widgets -> widgetsFragment.show()
        R.id.overlay -> overlayFragment.show()
      }
      true
    }
  }

  private fun Fragment.show() {
    val fragment = this
    supportFragmentManager.commit { replace(R.id.fragment_container, fragment, null) }
  }

}
