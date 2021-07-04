package com.example.news.data.local.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_header")
data class NewsCacheEntity(
    @PrimaryKey
    val id: String,
    val description: String,
    val category: String
) {
    override fun equals(other: Any?): Boolean {
        return category == (other as NewsCacheEntity).category
                && description == other.description

    }
}