package com.example.news.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.news.ui.headlines.HeadLinesAdapter
import com.example.news.ui.headlines.HeadLinesFragment
import com.example.news.ui.news.NewsFragment
import com.example.news.ui.news.SourcesAdapter
import javax.inject.Inject

class CustomFragmentFactory
@Inject constructor(
    private val sourcesAdapter: SourcesAdapter,
    private val headLinesAdapter: HeadLinesAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            NewsFragment::class.java.name -> NewsFragment(sourceAdapter = sourcesAdapter)
            HeadLinesFragment::class.java.name -> HeadLinesFragment(headLinesAdapter = headLinesAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}