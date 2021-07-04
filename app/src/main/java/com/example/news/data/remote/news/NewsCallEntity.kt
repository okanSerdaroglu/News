package com.example.news.data.remote.news

class NewsCallEntity(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) {
    override fun equals(other: Any?): Boolean {
        return id == (other as NewsCallEntity).id
                && description == other.description
                && category == other.category
    }
}