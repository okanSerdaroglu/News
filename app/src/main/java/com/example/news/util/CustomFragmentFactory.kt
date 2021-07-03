package com.example.news.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.news.ui.NewsFragment
import javax.inject.Inject

class CustomFragmentFactory
@Inject constructor(

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            NewsFragment::class.java.name -> NewsFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}