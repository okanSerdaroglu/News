package com.example.news.ui.news

import com.example.news.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase
@Inject
constructor(
    private val newsRepository: NewsRepository
) {
    fun getAllNews(pageNumber: Int) = newsRepository.getAllNews(pageNumber)
}