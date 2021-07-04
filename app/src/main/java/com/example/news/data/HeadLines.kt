package com.example.news.data

data class HeadLines(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val isInReadList: Boolean
) {
    override fun equals(other: Any?): Boolean {
        return title == (other as HeadLines).title
                && description == other.description
                && url == other.url
    }
}
