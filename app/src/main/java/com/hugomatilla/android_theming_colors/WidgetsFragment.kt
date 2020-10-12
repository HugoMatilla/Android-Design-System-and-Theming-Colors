package com.hugomatilla.android_theming_colors

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WidgetsFragment : Fragment() {

  private lateinit var contextThemeWrapper: Context

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_widgets, container, false)
  }

}