package com.example.news.data.local.headlines

import com.example.news.SharedModel
import com.google.common.truth.Truth
import org.junit.Test

class HeadLinesCacheMapperTest {

    private val headLinesCacheMapper = HeadLinesCacheMapper()

    @Test
    fun `map from entity success`() {
        val cacheHeadLines = SharedModel.cacheHeadLines
        val mappedHeadLines = headLinesCacheMapper.mapFromEntity(cacheHeadLines)
        Truth.assertThat(mappedHeadLines).isEqualTo(SharedModel.headLines)
    }

    @Test
    fun `map to entity success`() {
        val headLines = SharedModel.headLines
        val mappedHeadLines = headLinesCacheMapper.mapToEntity(headLines)
        Truth.assertThat(mappedHeadLines).isEqualTo(SharedModel.cacheHeadLines)
    }

}