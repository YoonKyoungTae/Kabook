package dev.androidblog.kabook.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitProvider {

    private val okHttpClient = OkHttpClient.Builder().addInterceptor {
        it.proceed(
            it.request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK 05415a3c0f6273113c12f7071ddc033e")
                .build()
        )
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient.build())
        .baseUrl("https://dapi.kakao.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIs = retrofit.create(APIs::class.java)

}