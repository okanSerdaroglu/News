package com.example.news.data.local.headlines

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headlines")
data class HeadLinesCacheEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val isInReadList: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return title == (other as HeadLinesCacheEntity).title
                && description == other.description
                && urlToImage == other.urlToImage
                && url == other.url
    }
}
