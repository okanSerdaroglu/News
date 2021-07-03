package com.example.news.ui.news

import androidx.hilt.lifecycle.ViewModelInject

class NewsViewModel
@ViewModelInject
constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel()
