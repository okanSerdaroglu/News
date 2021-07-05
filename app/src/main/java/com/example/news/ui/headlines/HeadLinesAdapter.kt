package com.example.news.ui.headlines

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.data.HeadLines
import com.example.news.databinding.ItemHeadLineBinding
import com.example.news.util.OnItemClickListener
import com.example.news.util.setUrl
import javax.inject.Inject

class HeadLinesAdapter
@Inject
constructor(
) : ListAdapter<HeadLines, HeadLinesAdapter.HeadLinesViewHolder>(NewsDiffCallback()) {

    private var listener: OnItemClickListener<HeadLines>? = null
    private var updateListener: OnItemClickListener<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLinesViewHolder {
        return HeadLinesViewHolder(ItemHeadLineBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HeadLinesViewHolder, position: Int) {
        holder.onBind(getItem(position), listener, updateListener)
    }

    fun setOnItemClick(listener : OnItemClickListener<HeadLines>?) {
        this.listener = listener
    }

    fun setUpdateListener(updateListener: OnItemClickListener<Int>) {
        this.updateListener = updateListener
    }

    override fun submitList(list: List<HeadLines>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    fun updateHeadLines(position: Int) {
        notifyItemChanged(position)
    }

    class HeadLinesViewHolder(private val binding: ItemHeadLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: HeadLines?,
            listener: OnItemClickListener<HeadLines>?,
            updateListener: OnItemClickListener<Int>?
        ) {
            item?.let { headLines ->
                binding.textViewTitle.text = headLines.title
                binding.textViewDescription.text = headLines.description
                binding.imageViewHeadLine.setUrl(headLines.urlToImage)
                binding.textViewAddToList.apply {
                    text = context.resources.getString(item.getAddToListTitle())
                    setOnClickListener {
                        updateListener?.onItemClick(adapterPosition)
                    }
                }
                binding.root.setOnClickListener {
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