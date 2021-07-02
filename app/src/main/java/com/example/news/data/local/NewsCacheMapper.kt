package com.example.news.data.local

import com.example.news.data.News
import com.example.news.util.EntityMapper

class NewsCacheMapper : EntityMapper<NewsCacheEntity, News> {

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