package dev.androidblog.kabook.view.detail

import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.toNumber
import dev.androidblog.kabook.util.toYearMonth


class BookDetailPresenter(override val view: BookDetailContract.View) : BookDetailContract.Presenter {

    override fun setBook(book: BookDAO.Documents) {
        with(book) {
            view.setBookTitle(title)
            view.setBookContents(makeContentsStr(contents))
            view.setBookUrl(url)
            view.setBookISBN(makeISBN(isbn))
            view.setBookDatetime(datetime.toYearMonth())
            view.setBookAuthors(makeAuthorsStr(authors.toString()))
            view.setBookPublisher(makePublisherStr(publisher))
            view.setBookPrice(makePriceStr(price))
            view.setBookSalePrice(makeSalePriceStr(sale_price))
            view.setBookThumbnail(thumbnail)
            view.setBookStatus(status)
            makeTranslatorsStr(translators)?.let {
                view.setBookTranslators(it)
            }
        }
    }

    private fun makeContentsStr(contents: String): String =
        if (contents.isEmpty()) {
            "-"
        } else {
            contents
        }

    private fun makeISBN(isbn: String): String = "ISBN : $isbn"

    private fun makeAuthorsStr(authors: String): String = "지은이 : ${authors.replace("[", "").replace("]", "")}"

    private fun makeTranslatorsStr(authors: ArrayList<String>): String? = if (authors.isNullOrEmpty()) {
        null
    } else {
        "번역 : ${authors.toString().replace("[", "").replace("]", "")}"
    }

    private fun makePublisherStr(publisher: String): String = "출판사 : $publisher"

    private fun makePriceStr(price: Int): String = "(정가 ${price.toNumber()}원)"

    private fun makeSalePriceStr(salePrice: Int): String = if (salePrice >= 0) {
        "${salePrice.toNumber()}원"
    } else {
        "0원"
    }

    override fun detachView() {
        //
    }

}