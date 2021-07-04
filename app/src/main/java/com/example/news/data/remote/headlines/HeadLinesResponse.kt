package com.example.news.data.remote.headlines

data class HeadLinesResponse(
    val totalResults: Int,
    val articles: List<HeadLinesCallEntity>?
)