package com.example.networkingandretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingandretrofit.databinding.ActivityNewsListBinding
import com.example.networkingandretrofit.viewmodel.NewsViewModel

class NewsListActivity : AppCompatActivity() {
    lateinit var binding : ActivityNewsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDataNews()
        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddNewsActivity::class.java))
        }
    }

    fun showDataNews(){
        val viewModelNews = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModelNews.callApiNews()
        viewModelNews.liveDataNews.observe(this, Observer {
            if (it != null){
                binding.rvNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvNews.adapter = NewsAdapter(it)
            }
        })
    }


}