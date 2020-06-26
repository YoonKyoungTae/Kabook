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

    override fun setBookThumnail(url: String) {
        iv_book_image.simpleLoadUrl(url)
    }

}