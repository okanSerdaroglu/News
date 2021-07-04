package com.example.news.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerScrollListener(private val mLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            val visibleItemCount = mLayoutManager.childCount
            val totalItemCount = mLayoutManager.itemCount - 3
            val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()
            if (!isLoading) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && hasMoreItem
                ) {
                    loadMoreItems()
                }
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract val isLoading: Boolean
    abstract val hasMoreItem: Boolean
}