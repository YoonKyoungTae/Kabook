package dev.androidblog.kabook.view.list

import dev.androidblog.kabook.util.BOOK_LIST_DEFAULT_PAGE
import dev.androidblog.kabook.view.base.BaseContract
import dev.androidblog.kabook.view.list.adapter.BookListAdapter

interface BookListContract {

    interface View : BaseContract.View {

        fun showLastItemToast()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun setBookListAdapter(listAdapter: BookListAdapter)

        fun searchBook(query: String, page: Int = BOOK_LIST_DEFAULT_PAGE)

        fun loadNext()
    }

}