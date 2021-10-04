package com.dicoding.favorite.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.core.domain.model.News
import com.dicoding.core.utils.FormatUtil
import com.dicoding.favorite.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private var news: List<News>,
    private var listener: OnAdapterListener,
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.news = news
            binding.format = FormatUtil()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = news[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            listener.onClick(news)
        }
    }

    interface OnAdapterListener {
        fun onClick(news: News)
    }
}