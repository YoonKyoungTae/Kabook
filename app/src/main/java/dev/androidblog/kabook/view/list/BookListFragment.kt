package dev.androidblog.kabook.view.list

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import dev.androidblog.kabook.R
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.BOOK_LIST_DEFAULT_PAGE
import dev.androidblog.kabook.util.toast
import dev.androidblog.kabook.view.base.BaseFragment
import dev.androidblog.kabook.view.list.adapter.BookListAdapter
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookListFragment : BaseFragment(R.layout.fragment_book_list), BookListContract.View {

    private val presenter: BookListContract.Presenter by lazy {
        BookListPresenter(this)
    }

    private lateinit var bookListAdapter: BookListAdapter
    var onClickBookItem: OnClickBookItem? = null

    companion object {
        fun newInstance() = BookListFragment()
            .apply {
                arguments = bundleOf()
            }
    }

    override fun initView() {
        initListAdapter()
    }

    override fun initOnClickListener() {
        iv_search_btn.setOnClickListener {
            val query = et_search_bar.text.toString()
            presenter.searchBook(query, BOOK_LIST_DEFAULT_PAGE)
        }
    }

    private fun initListAdapter() {
        bookListAdapter = BookListAdapter({
            onClickBookItem?.onClick(it)
        }) {
            presenter.loadNext()
        }

        rv_book_list.layoutManager = LinearLayoutManager(requireActivity())
        rv_book_list.adapter = bookListAdapter

        presenter.setBookListAdapter(bookListAdapter)
    }

    interface OnClickBookItem {
        fun onClick(book: BookDAO.Documents)
    }

    override fun showLastItemToast() {
        getString(R.string.toast_last_item).toast(requireActivity())
    }
}