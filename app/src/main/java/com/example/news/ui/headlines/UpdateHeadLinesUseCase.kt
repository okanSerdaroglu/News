package com.example.news.ui.headlines

import com.example.news.repository.headlines.HeadLinesRepository
import javax.inject.Inject

class UpdateHeadLinesUseCase
@Inject
constructor(
    private val headLinesRepository: HeadLinesRepository
){
    suspend fun updateHeadLine(isInReadList:Boolean,title:String)
    = headLinesRepository.updateHeadLinesReadListStatus(isInReadList,title)
}