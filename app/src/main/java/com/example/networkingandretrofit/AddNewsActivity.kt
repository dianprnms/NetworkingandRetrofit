package com.example.networkingandretrofit

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.networkingandretrofit.databinding.ActivityAddNewsBinding
import com.example.networkingandretrofit.viewmodel.NewsViewModel

class AddNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            val title = binding.addTitle.text.toString()
            val img = binding.addImage.text.toString()
            val author = binding.addAuthor.text.toString()
            val desc = binding.addDescription.text.toString()
            addNews(title, img, author, desc)
        }
    }

    fun addNews(title : String, image : String, author :String, desc : String){
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.addDataNews(title, image, author, desc)
        viewModel.postNews().observe(this, {
            if(it != null){
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        })
    }
}