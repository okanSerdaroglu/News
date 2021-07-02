package com.example.news.data.local

data class NewsCacheEntity(
    val id: String,
    val description: String,
    val category: String
) {
    override fun equals(other: Any?): Boolean {
        return id == (other as NewsCacheEntity).id
                && description == other.description
                && category == other.category
    }
}