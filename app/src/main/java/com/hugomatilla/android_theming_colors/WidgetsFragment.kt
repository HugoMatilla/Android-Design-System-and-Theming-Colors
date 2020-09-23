package com.hugomatilla.android_theming_colors

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.hugomatilla.android_theming_colors.R.style

class WidgetsFragment : Fragment() {

  private lateinit var contextThemeWrapper: Context

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    contextThemeWrapper = ContextThemeWrapper(activity, style.Theme_MyApp)
    return inflater.inflate(R.layout.fragment_widgets, container, false)
  }

}