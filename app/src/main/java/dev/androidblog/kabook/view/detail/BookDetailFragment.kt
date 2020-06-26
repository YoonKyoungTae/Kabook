package dev.androidblog.kabook.view.detail

import androidx.core.os.bundleOf
import dev.androidblog.kabook.R
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.simpleLoadUrl
import dev.androidblog.kabook.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookDetailFragment : BaseFragment(R.layout.fragment_book_detail), BookDetailContract.View {

    private val presenter: BookDetailContract.Presenter by lazy {
        BookDetailPresenter(this)
    }

    companion object {

        private const val EXTRA_BOOK = "EXTRA_BOOK"

        fun newInstance(book: BookDAO.Documents) = BookDetailFragment()
            .apply {
                arguments = bundleOf(
                    EXTRA_BOOK to book
                )
            }
    }

    override fun initView() {
        val book = arguments?.get(EXTRA_BOOK) as? BookDAO.Documents
        book?.let {
            presenter.setBook(book)
        }
    }

    override fun initOnClickListener() {
        iv_back_arrow.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun setBookTitle(title: String) {
        tv_book_title.text = title
    }

    override fun setBookContents(contents: String) {
        tv_book_contents.text = contents
    }

    override fun setBookUrl(url: String) {
        //
    }

    override fun setBookISBN(isbn: String) {
        //
    }

    override fun setBookDatetime(datetime: String) {
        tv_book_datetime.text = datetime
    }

    override fun setBookAuthors(authors: String) {
        tv_book_authors.text = authors
    }

    override fun setBookPublisher(publisher: String) {
        tv_book_publisher.text = publisher
    }

    override fun setBookTranslators(translators: String) {
        //
    }

    override fun setBookPrice(price: String) {
        tv_book_price.text = price
    }

    override fun setBookSalePrice(salePrice: String) {
        tv_book_sale_price.text = salePrice
    }

    override fun setBookThumbnail(url: String) {
        iv_book_image.simpleLoadUrl(url)
    }

    override fun setBookStatus(status: String) {
        tv_book_status.text = status
    }

}