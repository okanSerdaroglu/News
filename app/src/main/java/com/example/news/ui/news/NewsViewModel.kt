package com.example.news.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.data.News
import com.example.news.ui.news.usecases.news.GetNewsUseCase
import com.example.news.util.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel
@ViewModelInject
constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    fun getAllNews() {
        viewModelScope.launch {
            getNewsUseCase.getAllNews().collect { resourceListNews ->
                when (resourceListNews.status) {
                    is Status.SUCCESS -> {
                        _newsList.value = resourceListNews.data
                    }
                    else -> {
                        handleErrorLoadingResource(resourceListNews)
                    }
                }
            }
        }
    }
}
