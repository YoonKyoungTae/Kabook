package dev.androidblog.kabook.data

import dev.androidblog.kabook.api.BaseCallBack
import dev.androidblog.kabook.api.dao.BookDAO

object BookRepo : BookRepoContract {

    private val remote = BookRemoteDataSource()

    override fun getSearchBook(query: String, size: Int, page: Int, callback: BaseCallBack<BookDAO>) {
        remote.getSearchBook(query, size, page, callback)
    }

}