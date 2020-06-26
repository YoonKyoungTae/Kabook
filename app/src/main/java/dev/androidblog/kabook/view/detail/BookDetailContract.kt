package dev.androidblog.kabook.view.detail

import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.view.base.BaseContract

interface BookDetailContract {

    interface View : BaseContract.View {

        fun setBookTitle(title: String)

        fun setBookThumnail(url: String)

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun setBook(book: BookDAO.Documents)
    }

}