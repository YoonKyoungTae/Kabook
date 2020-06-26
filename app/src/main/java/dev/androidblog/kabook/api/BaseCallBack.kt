package dev.androidblog.kabook.api

abstract class BaseCallBack<T> {

    abstract fun onLoaded(data: T)

    abstract fun onFailed()

}