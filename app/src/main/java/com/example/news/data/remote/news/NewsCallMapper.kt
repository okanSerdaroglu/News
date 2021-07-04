package com.example.news.data.remote.news

import com.example.news.data.News
import com.example.news.util.EntityMapper
import javax.inject.Inject

class NewsCallMapper
@Inject
constructor() : EntityMapper<NewsCallEntity, News> {

    override fun mapFromEntity(entityModel: NewsCallEntity) = News(
        id = entityModel.id,
        description = entityModel.description,
        category = entityModel.category,
    )

    override fun mapToEntity(domainModel: News) = NewsCallEntity(
        id = domainModel.id,
        name = "",
        description = domainModel.description,
        url = "",
        category = domainModel.category,
        language = "",
        country = ""
    )

}