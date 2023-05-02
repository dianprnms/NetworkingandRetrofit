package com.example.networkingandretrofit

import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkingandretrofit.databinding.ItemNewsBinding
import com.example.networkingandretrofit.model.ResponseDataNews
import com.example.networkingandretrofit.model.ResponseDataNewsItem

class NewsAdapter(var listNews : List<ResponseDataNewsItem>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    var view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder((view))    }

    override fun getItemCount(): Int {
    return listNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleNews.text = listNews[position].title
        holder.binding.datenews.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].image).into(holder.binding.imgNews)

        holder.binding.iconupdate.setOnClickListener {
            var edit = Intent(it.context, UpdateNewsActivity::class.java)
            edit.putExtra("update", listNews[position].id)
            it.context.startActivity(edit)
        }




    }

}