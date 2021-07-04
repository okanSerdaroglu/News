package com.example.news.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.news.ui.NewsFragment
import com.example.news.ui.SourcesAdapter
import javax.inject.Inject

class CustomFragmentFactory
@Inject constructor(
    private val sourcesAdapter: SourcesAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            NewsFragment::class.java.name -> NewsFragment(sourceAdapter = sourcesAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}