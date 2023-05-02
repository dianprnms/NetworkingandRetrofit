package com.example.networkingandretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingandretrofit.databinding.ActivityMainBinding
import com.example.networkingandretrofit.model.ResponseDataNewsItem
import com.example.networkingandretrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDatanews()

        binding.addButton.setOnClickListener{
            startActivity(Intent(this,AddNewsActivity::class.java))
        }
    }

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
}