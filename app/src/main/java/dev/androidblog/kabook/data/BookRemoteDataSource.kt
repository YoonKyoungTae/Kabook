package dev.androidblog.kabook.data

import dev.androidblog.kabook.api.BaseCallBack
import dev.androidblog.kabook.api.RetrofitProvider.api
import dev.androidblog.kabook.api.dao.BookDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRemoteDataSource : BookRepoContract {
    override fun getSearchBook(query: String, size: Int, page: Int, callback: BaseCallBack<BookDAO>) {
        api.getSearchBook(query, size, page).enqueue(object : Callback<BookDAO> {
            override fun onResponse(call: Call<BookDAO>, response: Response<BookDAO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onLoaded(it)
                        return
                    }
                }

                callback.onFailed()
            }

            override fun onFailure(call: Call<BookDAO>, t: Throwable) {
                callback.onFailed()
            }
        })
    }
}