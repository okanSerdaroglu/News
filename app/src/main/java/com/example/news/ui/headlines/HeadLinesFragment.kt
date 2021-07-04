package com.example.news.ui.headlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.data.HeadLines
import com.example.news.databinding.FragmentHeadLinesBinding
import com.example.news.ui.NewsViewModel
import com.example.news.util.OnItemClickListener
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
        binding?.recyclerViewHeadLines?.apply {
            setHasFixedSize(true)
            adapter = headLinesAdapter
            addItemDecoration(
                SpaceItemDecoration(
                    resources.getDimension(R.dimen.space_16).toInt()
                )
            )
        }

        subscribeObserver()

    }

    private fun subscribeObserver() {
        viewModel?.headLinesList?.observe(viewLifecycleOwner, { headLines ->
            headLinesAdapter.submitList(headLines)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClick(item: HeadLines) {
        
    }
}