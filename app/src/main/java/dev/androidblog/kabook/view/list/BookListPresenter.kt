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

    override fun setBookListAdapter(listAdapter: BookListAdapter) {
        this.bookListAdapter = listAdapter
    }

    override fun searchBook(query: String, page: Int) {
        this.query = query
        this.page = page

        BookRepo.getSearchBook(query = query, page = page, callback = object : BaseCallBack<BookDAO>() {
            override fun onLoaded(data: BookDAO) {
                if (page == BOOK_LIST_DEFAULT_PAGE) {
                    refreshList(data.documents)
                } else {
                    addList(data.documents)
                }
            }

            override fun onFailed() {

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