package dev.androidblog.kabook.view.main

import dev.androidblog.kabook.view.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {

    }

}