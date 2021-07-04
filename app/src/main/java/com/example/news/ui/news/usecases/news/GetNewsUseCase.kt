package com.example.news.ui.news.usecases.news

import com.example.news.repository.news.NewsRepository
import javax.inject.Inject

class GetNewsUseCase
@Inject
constructor(
    private val newsRepository: NewsRepository
) {
    fun getAllNews() = newsRepository.getAllNews()
}