package com.example.news.data.local.news

import com.example.news.data.News
import com.example.news.util.EntityMapper
import javax.inject.Inject

class NewsCacheMapper
@Inject
constructor() : EntityMapper<NewsCacheEntity, News> {

    override fun mapToEntity(domainModel: News): NewsCacheEntity = NewsCacheEntity(
        domainModel.id,
        domainModel.description,
        domainModel.category
    )

    override fun mapFromEntity(entityModel: NewsCacheEntity) = News(
        entityModel.id,
        entityModel.description,
        entityModel.category
    )
}