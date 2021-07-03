package com.example.news.data


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