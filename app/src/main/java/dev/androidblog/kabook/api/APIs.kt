package dev.androidblog.kabook.api

import dev.androidblog.kabook.api.dao.BookDAO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("v3/search/book")
    fun getSearchBook(
        @Query("query") query: String,
        @Query("size") size: Int = 50,
        @Query("page") page: Int = 1
    ) : Call<BookDAO>

}