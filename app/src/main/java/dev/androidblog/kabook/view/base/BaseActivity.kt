package dev.androidblog.kabook.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dev.androidblog.kabook.view.list.BookListViewModel

abstract class BaseActivity(@LayoutRes layout: Int) : AppCompatActivity(layout) {

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

}