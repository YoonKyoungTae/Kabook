package dev.androidblog.kabook.view.detail

import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.toNumber
import dev.androidblog.kabook.util.toYearMonth


class BookDetailPresenter(override val view: BookDetailContract.View) : BookDetailContract.Presenter {

    private lateinit var book: BookDAO.Documents

    override fun setBook(book: BookDAO.Documents) {
        this.book = book

        view.setBookTitle(book.title)

        view.setBookContents(book.contents)

        view.setBookUrl(book.url)

        view.setBookISBN(book.isbn)

        view.setBookDatetime(book.datetime.toYearMonth())

        view.setBookAuthors("지은이 : ${book.authors.toString().replace("[", "").replace("]", "")}")

        view.setBookPublisher("출판사 : ${book.publisher}")

//        view.setBookTranslators(book.translators)

        view.setBookPrice("(정가 ${book.price.toNumber()}원)")

        view.setBookSalePrice(if (book.sale_price >= 0) {
            "${book.sale_price.toNumber()}원"
        } else {
            "0원"
        })

        view.setBookThumbnail(book.thumbnail)

        view.setBookStatus(book.status)

    }

    override fun detachView() {
        //
    }

}