package dev.androidblog.kabook.view.list

import dev.androidblog.kabook.api.BaseCallBack
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.data.BookRepo
import dev.androidblog.kabook.util.BOOK_LIST_DEFAULT_PAGE
import dev.androidblog.kabook.view.list.adapter.BookListAdapter


class BookListPresenter(override val view: BookListContract.View) : BookListContract.Presenter {

    private lateinit var query: String
    private lateinit var bookListAdapter: BookListAdapter
    private var page: Int = BOOK_LIST_DEFAULT_PAGE
    private var isEnd: Boolean = false

    override fun setBookListAdapter(listAdapter: BookListAdapter) {
        this.bookListAdapter = listAdapter
    }

    override fun searchBook(query: String, page: Int) {
        if (page != BOOK_LIST_DEFAULT_PAGE && isEnd) {
            view.showLastItemToast()
            return
        } else {
            isEnd = false
        }

        this.query = query
        this.page = page

        BookRepo.getSearchBook(query = query, page = page, callback = object : BaseCallBack<BookDAO>() {
            override fun onLoaded(data: BookDAO) {
                isEnd = data.meta.is_end

                if (data.documents.size > 0) {
                    view.hidePlaceHolder()
                } else {
                    view.showError()
                    view.showPlaceHolder()
                }

                if (page == BOOK_LIST_DEFAULT_PAGE) {
                    refreshList(data.documents)
                } else {
                    addList(data.documents)
                }
            }

            override fun onFailed() {
                view.showError()
                view.showPlaceHolder()
            }
        })
    }

    override fun loadNext() {
        searchBook(query, ++page)
    }

    private fun refreshList(list: ArrayList<BookDAO.Documents>) {
        bookListAdapter.bookList.clear()
        bookListAdapter.bookList.addAll(list)
        bookListAdapter.notifyDataSetChanged()
    }

    private fun addList(list: ArrayList<BookDAO.Documents>) {
        bookListAdapter.bookList.addAll(list)
        bookListAdapter.notifyDataSetChanged()
    }

    override fun detachView() {

    }

}