package com.hugomatilla.android_theming_colors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
