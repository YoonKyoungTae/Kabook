package dev.androidblog.kabook.view.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layout: Int) : Fragment(layout) {

    abstract fun initView()
    abstract fun initOnClickListener()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClickListener()
    }
}