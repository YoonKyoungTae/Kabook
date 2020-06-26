package dev.androidblog.kabook.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.androidblog.kabook.R
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.util.gone
import dev.androidblog.kabook.util.simpleLoadUrl
import dev.androidblog.kabook.util.toNumber
import dev.androidblog.kabook.util.toYearMonth
import dev.androidblog.kabook.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_book.view.*

class BookListAdapter(
    private val itemClickListener: (BookDAO.Documents) -> Unit,
    private val loadMoreListener: () -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    var bookList: ArrayList<BookDAO.Documents> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(bookList[position], itemClickListener)
        if (position == itemCount - 1) {
            loadMoreListener()
        }
    }
}

class ViewHolder(
    parent: ViewGroup
) : BaseViewHolder<BookDAO.Documents>(
    LayoutInflater.from(parent.context).inflate(R.layout.listitem_book, parent, false)
) {
    override fun bind(data: BookDAO.Documents, itemClickListener: (BookDAO.Documents) -> Unit) {
        with(itemView) {
            tv_book_title.text = data.title
            tv_book_authors.text = "지은이 : ${data.authors.toString().replace("[", "").replace("]", "")}"
            tv_book_publisher.text = "출판사 : ${data.publisher}"
            tv_book_datetime.text = data.datetime.toYearMonth()

            if (data.status.isEmpty()) {
                tv_book_status.gone()
            } else {
                tv_book_status.text = data.status
            }

            tv_book_price.text = "(정가 ${data.price.toNumber()}원)"
            tv_book_sale_price.text = if (data.sale_price >= 0) {
                "${data.sale_price.toNumber()}원"
            } else {
                "0원"
            }

            iv_book_image.simpleLoadUrl(data.thumbnail)

            setOnClickListener {
                itemClickListener(data)
            }
        }
    }

}