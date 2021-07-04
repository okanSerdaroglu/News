package com.example.news.data.remote.headlines

import com.example.news.data.HeadLines
import com.example.news.util.EntityMapper
import javax.inject.Inject

class HeadLinesCallMapper
@Inject
constructor() : EntityMapper<HeadLinesCallEntity, HeadLines> {
    override fun mapFromEntity(entityModel: HeadLinesCallEntity) = HeadLines(
        title = entityModel.title,
        description = entityModel.description,
        url = entityModel.url,
        urlToImage = entityModel.urlToImage,
        isInReadList = false
    )

    override fun mapToEntity(domainModel: HeadLines) = HeadLinesCallEntity(
        source = null,
        author = "",
        title = domainModel.title,
        description = domainModel.description,
        url = domainModel.url,
        urlToImage = domainModel.urlToImage,
        publishedAt = "",
        content = ""
    )

}