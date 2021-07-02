package com.example.news

import com.example.news.data.News
import com.example.news.data.local.NewsCacheEntity
import com.example.news.data.remote.NewsCallEntity

object SharedModel {

    val callNews = NewsCallEntity(
        id = "abc-news",
        name = "ABC News",
        description = "Your trusted source for breaking news, analysis, " +
                "exclusive interviews, headlines, and videos at ABCNews.com.",
        url = "https://abcnews.go.com",
        category = "general",
        language = "en",
        country = "us"
    )

    val news = News(
        id = "abc-news",
        description = "Your trusted source for breaking news, analysis, " +
                "exclusive interviews, headlines, and videos at ABCNews.com.",
        category = "general"
    )

    val cacheNews = NewsCacheEntity(
        id = "abc-news",
        description = "Your trusted source for breaking news, analysis, " +
                "exclusive interviews, headlines, and videos at ABCNews.com.",
        category = "general"
    )

    val callNews_2 = NewsCallEntity(
        id = "abc-news-au",
        name = "ABC News (AU)",
        description = "Australia's most trusted source of local, national and world news. " +
                "Comprehensive, independent, in-depth analysis, " +
                "the latest business, sport, weather and more.",
        url = "http://www.abc.net.au/news",
        category = "general",
        language = "en",
        country = "au"
    )

    val news_2 = News(
        id = "abc-news-au",
        description = "Australia's most trusted source of local, national and world news. " +
                "Comprehensive, independent, in-depth analysis, " +
                "the latest business, sport, weather and more.",
        category = "general"
    )

    val cacheNews_2 = NewsCacheEntity(
        id = "abc-news-au",
        description = "Australia's most trusted source of local, national and world news. " +
                "Comprehensive, independent, in-depth analysis, " +
                "the latest business, sport, weather and more.",
        category = "general"
    )


}