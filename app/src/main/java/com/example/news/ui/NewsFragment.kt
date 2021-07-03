package com.example.news.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.ui.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment
constructor(
    var viewModel: NewsViewModel? = null
) : Fragment(R.layout.fragment_news) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("newsList", "worked")
        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        subscribeObserver()
        viewModel?.getAllNews(1)
    }

    private fun subscribeObserver() {
        viewModel?.newsList?.observe(viewLifecycleOwner, { newsList ->
            Log.d("newsList", newsList.toString())
        })
    }
}