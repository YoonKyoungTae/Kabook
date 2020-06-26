package dev.androidblog.kabook.view.list

import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.androidblog.kabook.R
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.*
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
        initEditText()
    }

    override fun initOnClickListener() {
        iv_search_btn.setOnClickListener {
            search()
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
        rv_book_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == 1) {
                    iv_shadow.visibility = View.VISIBLE
                    iv_shadow.animation = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
                } else if (newState == 0) {
                    if (!rv_book_list.canScrollVertically(-1)) {
                        iv_shadow.visibility = View.GONE
                        iv_shadow.animation = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
                    }
                }
            }

        })
        presenter.setBookListAdapter(bookListAdapter)
    }

    private fun initEditText() {
        et_search_bar.setOnEditorActionListener { v, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    search()
                }
            }

            true
        }
    }

    interface OnClickBookItem {
        fun onClick(book: BookDAO.Documents)
    }

    override fun showLastItemToast() {
        getString(R.string.toast_last_item).toast(requireActivity())
    }

    override fun showError() {
        getString(R.string.toast_error).toast(requireActivity())
    }

    override fun showPlaceHolder() {
        iv_kakao.visible()
    }

    override fun hidePlaceHolder() {
        iv_kakao.gone()
    }

    private fun search() {
        val query = et_search_bar.text.toString()
        presenter.searchBook(query, BOOK_LIST_DEFAULT_PAGE)
        hideKeyboard()
    }
}