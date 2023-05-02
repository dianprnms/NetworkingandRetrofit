package com.example.networkingandretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkingandretrofit.databinding.ActivityUpdateNewsBinding
import com.example.networkingandretrofit.viewmodel.NewsViewModel

class UpdateNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Updatebtn.setOnClickListener{
            var id = intent.getStringExtra("update")
//            var id = 2
            var title = binding.updateTitle.text.toString()
            var author = binding.updateAuthor.text.toString()
            var image = binding.updateImage.text.toString()
            var desc = binding.updateDescription.text.toString()
            updateDataNews(id!!.toInt(), title, author, image, desc)
        }
    }

    fun updateDataNews(id:Int, title:String, image:String, author:String, desc:String){
        var viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.callUpdateDataNews(id, title, image, author, desc)
        viewModel.putNews().observe(this,{
            if (it != null){
                Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
            }
        })
    }
}