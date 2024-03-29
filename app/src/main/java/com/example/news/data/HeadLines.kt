package com.example.news.data

import com.example.news.R

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

    fun getAddToListTitle() : Int{
        return when {
            isInReadList -> {
                R.string.remove_from_list
            }
            else -> {
                R.string.add_to_list
            }
        }
    }
}
