package com.example.news.ui.headlines

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.data.HeadLines
import com.example.news.databinding.ItemSourceBinding
import com.example.news.util.OnItemClickListener
import javax.inject.Inject

class HeadLinesAdapter
@Inject
constructor(
) : ListAdapter<HeadLines, HeadLinesAdapter.HeadLinesViewHolder>(NewsDiffCallback()) {

    private var listener: OnItemClickListener<HeadLines>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLinesViewHolder {
        return HeadLinesViewHolder(ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HeadLinesViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    fun setOnItemClick(listener : OnItemClickListener<HeadLines>?) {
        this.listener = listener
    }

    override fun submitList(list: List<HeadLines>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class HeadLinesViewHolder(private val binding: ItemSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: HeadLines?, listener: OnItemClickListener<HeadLines>?) {
            item?.let { headLines ->
                binding.textViewName.text = headLines.title
                binding.textViewDescription.text = headLines.description
                binding.constraintLayoutItem.setOnClickListener {
                    listener?.onItemClick(headLines)
                }
            }
        }
    }
}


class NewsDiffCallback : DiffUtil.ItemCallback<HeadLines>() {

    override fun areItemsTheSame(
        oldItem: HeadLines,
        newItem: HeadLines
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: HeadLines,
        newItem: HeadLines
    ): Boolean = oldItem == newItem

}