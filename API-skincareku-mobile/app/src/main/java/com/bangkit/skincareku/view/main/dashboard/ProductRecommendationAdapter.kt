package com.bangkit.skincareku.view.main.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.skincareku.databinding.ItemProductBinding
import com.bangkit.skincareku.networking.response.Product

class ProductRecommendationAdapter (private val productRecommendationList: ArrayList<Product>, private val viewModel: DashboardViewModel) :
    RecyclerView.Adapter<ProductRecommendationAdapter.ListViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(productRecommendationList[position])

    }

    override fun getItemCount(): Int = productRecommendationList.size

    class ListViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: Product) {
            binding.tvProductTitle.text = productItem.title
            binding.tvProductDescription.text = productItem.description
            binding.tvLikesCount.text = productItem.like.toString()
        }
    }

}