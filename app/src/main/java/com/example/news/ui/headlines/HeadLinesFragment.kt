package com.example.news.ui.headlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.data.HeadLines
import com.example.news.databinding.FragmentHeadLinesBinding
import com.example.news.ui.NewsViewModel
import com.example.news.util.OnItemClickListener
import com.example.news.util.RecyclerScrollListener
import com.example.news.util.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadLinesFragment
constructor(
    private val headLinesAdapter: HeadLinesAdapter,
    var viewModel: NewsViewModel? = null
) : Fragment(R.layout.fragment_head_lines), OnItemClickListener<HeadLines> {

    private var binding: FragmentHeadLinesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        binding = FragmentHeadLinesBinding.bind(view)
        headLinesAdapter.setOnItemClick(this)

        subscribeObserver()
    }


    private fun subscribeObserver() {
        viewModel?.apply {
            headLinesList.observe(viewLifecycleOwner, { headLines ->
                setAdapter()
                headLinesAdapter.submitList(headLines)
            })
            category.observe(viewLifecycleOwner, {
                viewModel?.getAllHeadLines(it)
            })
        }
    }

    private fun setAdapter () {
        binding?.recyclerViewHeadLines?.apply {
            setHasFixedSize(true)
            adapter = headLinesAdapter
            addItemDecoration(
                SpaceItemDecoration(
                    resources.getDimension(R.dimen.space_16).toInt()
                )
            )
            val scrollListener = object : RecyclerScrollListener(layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel?.loadMoreItems()
                }
                override val hasMoreItem: Boolean
                    get() = viewModel?.hasMoreItem() ?: false
                override val isLoading: Boolean
                    get() = false
            }
            addOnScrollListener(scrollListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClick(item: HeadLines) {

    }
}