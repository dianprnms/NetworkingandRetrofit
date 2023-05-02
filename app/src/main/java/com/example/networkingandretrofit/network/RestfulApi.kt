package com.example.networkingandretrofit.network

import com.example.networkingandretrofit.model.DataNews
import com.example.networkingandretrofit.model.ResponseAddNews
import com.example.networkingandretrofit.model.ResponseDataNewsItem
import com.example.networkingandretrofit.model.ResponseUpdateNews
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {

    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @POST("news")
    fun postDataNews(@Body request:DataNews) : Call<ResponseAddNews>

    @PUT("news/{id}")
    fun updateDataNews(
        @Path("id") id : Int,
        @Body request: DataNews
    ):Call<List<ResponseUpdateNews>>

    @DELETE("news/{id}")
    fun deleteDataNews(@Path("id") id :Int) : Call<Int>

}
