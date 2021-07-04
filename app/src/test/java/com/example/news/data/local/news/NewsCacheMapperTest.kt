package com.example.news.data.local.news

import com.example.news.SharedModel
import com.example.news.data.local.news.NewsCacheMapper
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class NewsCacheMapperTest {

    private val newsCacheMapper = NewsCacheMapper()

    @Test
    fun `map from entity success`() {
        val cacheNews = SharedModel.cacheNews
        val mappedNews = newsCacheMapper.mapFromEntity(cacheNews)
        assertThat(mappedNews).isEqualTo(SharedModel.news)
    }

    @Test
    fun `map to entity success`() {
        val news = SharedModel.news
        val mappedNews = newsCacheMapper.mapToEntity(news)
        assertThat(mappedNews).isEqualTo(SharedModel.cacheNews)
    }

}