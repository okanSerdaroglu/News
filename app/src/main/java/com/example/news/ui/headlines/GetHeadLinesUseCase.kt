package com.example.news.ui.headlines

import com.example.news.repository.headlines.HeadLinesRepository
import javax.inject.Inject

class GetHeadLinesUseCase
@Inject
constructor(
    private val headLinesRepository: HeadLinesRepository
) {
    fun getAllHeadLines(category:String, page:Int) = headLinesRepository.getAllHeadLines(category, page)
}