package dev.androidblog.kabook.data

import dev.androidblog.kabook.api.BaseCallBack
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.BOOK_LIST_DEFAULT_PAGE
import dev.androidblog.kabook.util.BOOK_LIST_DEFAULT_SIZE

interface BookRepoContract {

    fun getSearchBook(query: String, size: Int = BOOK_LIST_DEFAULT_SIZE, page: Int = BOOK_LIST_DEFAULT_PAGE, callback: BaseCallBack<BookDAO>)

}