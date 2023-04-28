package com.example.networkingandretrofit.network

import com.example.networkingandretrofit.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {

    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

}
