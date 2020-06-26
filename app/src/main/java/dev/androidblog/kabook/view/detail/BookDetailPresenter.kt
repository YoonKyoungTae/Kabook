package dev.androidblog.kabook.view.detail

import dev.androidblog.kabook.api.dao.BookDAO


class BookDetailPresenter(override val view: BookDetailContract.View) : BookDetailContract.Presenter {

    private lateinit var book: BookDAO.Documents

    override fun setBook(book: BookDAO.Documents) {
        this.book = book

        view.setBookTitle(book.title)
        view.setBookThumnail(book.thumbnail)
    }

    override fun detachView() {
        //
    }

}