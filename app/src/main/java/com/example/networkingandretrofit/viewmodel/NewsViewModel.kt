package com.example.networkingandretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingandretrofit.model.*
import com.example.networkingandretrofit.network.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    lateinit var liveDataNews: MutableLiveData<List<ResponseDataNewsItem>>
    lateinit var postDataNews: MutableLiveData<ResponseAddNews>
    lateinit var updateDataNews: MutableLiveData<List<ResponseUpdateNews>>
    lateinit var deleteDataNews: MutableLiveData<Int>

    init {
        liveDataNews = MutableLiveData()
        postDataNews = MutableLiveData()
        updateDataNews = MutableLiveData()
        deleteDataNews = MutableLiveData()
    }

    fun postNews ():MutableLiveData<ResponseAddNews>{
        return postDataNews
    }

    fun putNews(): MutableLiveData<List<ResponseUpdateNews>>{
        return updateDataNews
    }

    fun deleteNews():MutableLiveData<Int>{
        return deleteDataNews
    }

//    fun getCallApiNews(): MutableLiveData<List<ResponseDataNewsItem>>{
//        return liveDataNews
//    }

    fun callApiNews() {
        RetrofitClient.instance.getAllNews()
            .enqueue(object : Callback<List<ResponseDataNewsItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if (response.isSuccessful) {
                        liveDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    liveDataNews.postValue((null))
                }
            })
    }


//    fun postNews(title: String, image: String, author: String, description: String) {
//        RetrofitClient.instance.postDataNews(DataNews(title, image, author, description))
//            .enqueue(object : Callback<ResponseAddNews> {
//                override fun onResponse(
//                    call: Call<List<ResponseDataNews>>,
//                    response: Response<List<ResponseDataNewsItem>>
//                ) {
//                    if (response.isSuccessful) {
//                        postDataNews.postValue(response.body())
//                    } else {
//                        postDataNews.postValue(null)
//                    }
//                }
//
//                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
//                    postDataNews.postValue(null)
//                }
//
//
//            })
//    }


    fun addDataNews(title: String, image: String, author: String, description: String) {
        RetrofitClient.instance.postDataNews(DataNews(title, image, author, description))
            .enqueue(object : Callback<ResponseAddNews>{
                override fun onResponse(
                    call: Call<ResponseAddNews>,
                    response: Response<ResponseAddNews>
                ) {
                    if (response.isSuccessful){
                        postDataNews.postValue(response.body())
                    }else{
                        postDataNews.postValue(null)
                    }                }

                override fun onFailure(call: Call<ResponseAddNews>, t: Throwable) {
                    postDataNews.postValue(null)
                }
            })
    }

    fun callUpdateDataNews(id:Int, title: String, image: String, author: String, description: String){
        RetrofitClient.instance.updateDataNews(id, DataNews(title, image, author, description))
            .enqueue(object : Callback<List<ResponseUpdateNews>> {
                override fun onResponse(
                    call: Call<List<ResponseUpdateNews>>,
                    response: Response<List<ResponseUpdateNews>>
                ) {
                    if (response.isSuccessful) {
                        updateDataNews.postValue(response.body())
                    } else {
                        updateDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseUpdateNews>>, t: Throwable) {
                    updateDataNews.postValue(null)
                }


            })
    }
//    fun getAllNews() {
//        GlobalScope.launch {
//            val userDao = RetrofitClient.in(getApplication())!!.noteDao()
//            val listnote = userDao.getDataNote()
//            allNote.postValue(listnote)
//        }
//    }

    fun deleteDataNews(id:Int, title: String, image: String, author: String, description: String){
        RetrofitClient.instance.deleteDataNews(id)
            .enqueue(object : Callback<Int> {

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        deleteDataNews.postValue(response.body())
                    } else {
                        deleteDataNews.postValue(null)
                    }                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    deleteDataNews.postValue(null)
                }
            })
    }
}


