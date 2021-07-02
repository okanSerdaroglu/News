package com.example.news.data.remote

import com.example.news.data.News
import com.example.news.util.EntityMapper

class MewsCallMapper : EntityMapper<NewsCallEntity, News> {
    override fun mapFromEntity(entityModel: NewsCallEntity): News = News(
        id = entityModel.id,
        description = entityModel.description,
        category = entityModel.category,
    )

    override fun mapToEntity(domainModel: News): NewsCallEntity {
        TODO("Not yet implemented")
    }

}