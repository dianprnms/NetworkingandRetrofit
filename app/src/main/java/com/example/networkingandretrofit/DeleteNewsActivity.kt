package com.example.networkingandretrofit

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.networkingandretrofit.databinding.ActivityDeleteNewsBinding
import com.example.networkingandretrofit.databinding.ActivityUpdateNewsBinding
import com.example.networkingandretrofit.viewmodel.NewsViewModel

class DeleteNewsActivity : AppCompatActivity() {
    lateinit var binding:ActivityDeleteNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            var id = intent.getStringExtra("delete")
            var title = binding.updateTitle.text.toString()
            var author = binding.updateAuthor.text.toString()
            var image = binding.updateImage.text.toString()
            var desc = binding.updateDescription.text.toString()
            deleteDataNews(id!!.toInt(), title, author, image, desc)
        }
    }

    fun deleteDataNews(id:Int, title:String, image:String, author:String, desc:String){
        var viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.deleteDataNews(id, title, image, author, desc)
        viewModel.deleteNews().observe(this,{
            if (it != null){
                Toast.makeText(this, "Pesan Anda Telah Dihapus", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    fun alertClick(){
//        AlertDialog.Builder(this)
//            .setTitle("Hapus Pesan")
//            .setMessage("Apakah Anda Yakin Ingin Menghapus Pesan Ini?")
//            .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
//                Toast.makeText(this, "Pesan Anda Telah Dihapus", Toast.LENGTH_LONG).show()
//            })
//            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
//                Toast.makeText(this, "Pesan Anda Tidak Dapat Dihapus", Toast.LENGTH_LONG).show()
//            })
//            .show()
//
//}
}