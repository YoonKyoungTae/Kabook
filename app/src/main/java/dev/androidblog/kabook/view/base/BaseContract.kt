package dev.androidblog.kabook.view.base

interface BaseContract {

    interface View {

    }

    interface Presenter<V: View> {
        val view: V
        fun detachView()
    }

}