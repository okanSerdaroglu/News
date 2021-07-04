package com.example.news.ui.news.usecases.headlines

import com.example.news.repository.headlines.HeadLinesRepository
import javax.inject.Inject

class GetHeadLinesUseCase
@Inject
constructor(
    private val headLinesRepository: HeadLinesRepository
) {
    fun getAllHeadLines() = headLinesRepository.getAllHeadLines()
}