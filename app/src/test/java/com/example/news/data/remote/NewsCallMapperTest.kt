package com.example.news.data.remote

import com.example.news.SharedModel
import com.example.news.data.remote.news.NewsCallMapper
import com.google.common.truth.Truth.*
import org.junit.Test

class NewsCallMapperTest {

    private val newsCallMapper = NewsCallMapper()

    @Test
    fun `map from entity success`() {
        val callNews = SharedModel.callNews
        val mappedNews = newsCallMapper.mapFromEntity(callNews)
        assertThat(mappedNews).isEqualTo(SharedModel.news)

    }

    @Test
    fun `map to entity success`() {
        val news = SharedModel.news
        val mappedNews = newsCallMapper.mapToEntity(news)
        assertThat(mappedNews).isEqualTo(SharedModel.callNews)
    }
}