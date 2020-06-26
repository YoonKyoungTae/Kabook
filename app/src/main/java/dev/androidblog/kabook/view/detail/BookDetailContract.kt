package dev.androidblog.kabook.view.detail

import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.view.base.BaseContract

interface BookDetailContract {

    interface View : BaseContract.View {

        fun setBookTitle(title: String)

        fun setBookContents(contents: String)

        fun setBookUrl(url: String)

        fun setBookISBN(isbn: String)

        fun setBookDatetime(datetime: String)

        fun setBookAuthors(authors: String)

        fun setBookPublisher(publisher: String)

        fun setBookTranslators(translators: String)

        fun setBookPrice(price: String)

        fun setBookSalePrice(salePrice: String)

        fun setBookThumbnail(url: String)

        fun setBookStatus(status: String)

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun setBook(book: BookDAO.Documents)
    }

}