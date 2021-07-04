package com.example.news.ui.news

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.data.News
import com.example.news.databinding.ItemSourceBinding
import com.example.news.util.OnItemClickListener
import javax.inject.Inject

class SourcesAdapter
@Inject
constructor(
) : ListAdapter<News, SourcesAdapter.NewsViewHolder>(NewsDiffCallback()) {

    private var listener: OnItemClickListener<News>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    fun setOnItemClick(listener : OnItemClickListener<News>?) {
        this.listener = listener
    }

    override fun submitList(list: List<News>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class NewsViewHolder(private val binding: ItemSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: News?, listener: OnItemClickListener<News>?) {
            item?.let { news ->
                binding.textViewName.text = news.category
                binding.textViewDescription.text = news.description
                binding.constraintLayoutItem.setOnClickListener {
                    listener?.onItemClick(news)
                }
            }
        }
    }
}


class NewsDiffCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(
        oldItem: News,
        newItem: News
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: News,
        newItem: News
    ): Boolean = oldItem == newItem

}






