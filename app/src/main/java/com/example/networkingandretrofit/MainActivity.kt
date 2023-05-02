package com.example.networkingandretrofit

import android.content.Intent
import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingandretrofit.databinding.ActivityMainBinding
import com.example.networkingandretrofit.model.DataNews
import com.example.networkingandretrofit.model.ResponseDataNewsItem
import com.example.networkingandretrofit.network.RetrofitClient
import com.example.networkingandretrofit.viewmodel.NewsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel
    lateinit var retrofitClient : RetrofitClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDatanews()
//        newsVM()
//
//        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
//        newsViewModel.callApiNews()
//
//        newsViewModel.getCallApiNews().observe(this, Observer {
//            newsAdapter.setNewsData(it as ArrayList<ResponseDataNewsItem>)
//        })

        binding.addButton.setOnClickListener{
            startActivity(Intent(this,AddNewsActivity::class.java))
        }


    }

//    private fun newsVM() {
//        newsAdapter = NewsAdapter(ArrayList())
//        binding.rvNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.rvNews.adapter = newsAdapter
//        newsAdapter.notifyDataSetChanged()
//    }


    fun getDatanews(){
        RetrofitClient.instance.getAllNews().enqueue(object  : Callback<List<ResponseDataNewsItem>>{
            override fun onResponse(
                call: Call<List<ResponseDataNewsItem>>,
                response: Response<List<ResponseDataNewsItem>>
            ) {
                if(response.isSuccessful){
                    //show data
                    binding.rvNews.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvNews.adapter = NewsAdapter(response.body()!!)

                }else{
                    Toast.makeText(this@MainActivity, "Failed load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
            }

        })
    }

//
//    override fun onStart(){
//        super.onStart()
//        RetrofitClient.instance.getAllNews().enqueue(object  : Callback<List<ResponseDataNewsItem>>{
//            override fun onResponse(
//                call: Call<List<ResponseDataNewsItem>>,
//                response: Response<List<ResponseDataNewsItem>>
//            ) {
//                if (response.isSuccessful) {
//                    val news = response.body()
//                    runOnUiThread {
//                        newsAdapter = NewsAdapter(news!!)
//                        binding.rvNews.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                        binding.rvNews.adapter = newsAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }



}