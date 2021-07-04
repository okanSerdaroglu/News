package com.example.news.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.news.R
import com.example.news.data.News
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.ui.NewsViewModel
import com.example.news.util.OnItemClickListener
import com.example.news.util.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment
constructor(
    val sourceAdapter: SourcesAdapter,
    var viewModel: NewsViewModel? = null
) : Fragment(R.layout.fragment_news), OnItemClickListener<News> {

    private var binding: FragmentNewsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        binding = FragmentNewsBinding.bind(view)
        sourceAdapter.setOnItemClick(this)
        binding?.recyclerViewSources?.apply {
            setHasFixedSize(true)
            adapter = sourceAdapter
            addItemDecoration(
                SpaceItemDecoration(
                    resources.getDimension(R.dimen.space_16).toInt()
                )
            )
        }

        subscribeObserver()
        viewModel?.getAllNews()
    }

    private fun subscribeObserver() {
        viewModel?.newsList?.observe(viewLifecycleOwner, { newsList ->
            sourceAdapter.submitList(newsList)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClick(item: News) {
        viewModel?.setCategory(item.category)
        findNavController().navigate(R.id.action_newsFragment_to_headLinesFragment)
    }

}