package com.example.news.data.local.headlines

import com.example.news.data.HeadLines

import com.example.news.util.EntityMapper
import javax.inject.Inject

class HeadLinesCacheMapper
@Inject
constructor() : EntityMapper<HeadLinesCacheEntity, HeadLines> {

    override fun mapFromEntity(entityModel: HeadLinesCacheEntity) = HeadLines(
        title = entityModel.title,
        description = entityModel.description,
        url = entityModel.url,
        urlToImage = entityModel.urlToImage,
        isInReadList = entityModel.isInReadList
    )

    override fun mapToEntity(domainModel: HeadLines) = HeadLinesCacheEntity(
        id = null,
        title = domainModel.title,
        description = domainModel.description,
        url = domainModel.url,
        urlToImage = domainModel.urlToImage
    )
}
