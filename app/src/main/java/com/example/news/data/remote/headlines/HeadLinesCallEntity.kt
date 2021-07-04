package com.example.news.data.remote.headlines

data class HeadLinesCallEntity(
    val source: SourceCallEntity?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {
    override fun equals(other: Any?): Boolean {
        return title == (other as HeadLinesCallEntity).title
                && description == other.description
                && url == other.url
    }
}
