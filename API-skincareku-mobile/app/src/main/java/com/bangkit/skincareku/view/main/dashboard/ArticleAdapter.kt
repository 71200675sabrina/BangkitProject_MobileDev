package com.bangkit.skincareku.view.main.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.skincareku.databinding.ItemArticleBinding
import com.bangkit.skincareku.networking.response.Article

class ArticleAdapter (private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ListViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(articleList[position])

    }

    override fun getItemCount(): Int = articleList.size

    class ListViewHolder(var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(articleItem: Article) {
            binding.tvArticleTitle.text = articleItem.title
            binding.tvArticleTime.text = articleItem.duration.toString()
        }
    }
}