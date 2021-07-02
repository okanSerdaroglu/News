package com.example.news.data

import com.example.news.data.remote.NewsCallEntity

data class News(
    val id: String,
    val description: String,
    val category: String
) {
    override fun equals(other: Any?): Boolean {
        return id == (other as News).id
                && description == other.description
                && category == other.category
    }
}