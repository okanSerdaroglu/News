package com.example.news

import com.example.news.data.HeadLines
import com.example.news.data.News
import com.example.news.data.local.headlines.HeadLinesCacheEntity
import com.example.news.data.local.news.NewsCacheEntity
import com.example.news.data.remote.headlines.HeadLinesCallEntity
import com.example.news.data.remote.news.NewsCallEntity

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


    val callHeadLines = HeadLinesCallEntity(
        source = null,
        author = "Jason Hanna, Alanne Orjoux and Haley Brink, CNN",
        title = "Tropical Storm Elsa weakens and slows, but a tropical storm watch is in effect for South Florida - CNN",
        description = "Tropical Storm Elsa is a little weaker and slower Sunday " +
                "as it swirls away from Haiti toward Jamaica and Cuba, " +
                "according to the National Hurricane Center, but a tropical " +
                "storm watch is in effect for the Florida Keys.",
        url = "https://www.cnn.com/2021/07/04/weather/tropical-storm-elsa-sunday/index.html",
        urlToImage = "https://cdn.cnn.com/cnnnext/dam/assets/210704021246-tropical-storm-elsa-track-super-tease.jpg",
        publishedAt = "2021-07-04T06:35:00Z",
        content = "The Milwaukee Bucks held on for a Game 6 win over the Atlanta " +
                "Hawks in the Eastern Conference finals on Saturday night, " +
                "and have now advanced to the NBA Finals for the first time since 1974. " +
                "There, t… [+2154 chars]"
    )

    val headLines = HeadLines(
        title = "Tropical Storm Elsa weakens and slows, but a tropical storm watch is in effect for South Florida - CNN",
        description = "Tropical Storm Elsa is a little weaker and slower Sunday " +
                "as it swirls away from Haiti toward Jamaica and Cuba, " +
                "according to the National Hurricane Center, but a tropical " +
                "storm watch is in effect for the Florida Keys.",
        url = "https://www.cnn.com/2021/07/04/weather/tropical-storm-elsa-sunday/index.html",
        urlToImage = "https://cdn.cnn.com/cnnnext/dam/assets/210704021246-tropical-storm-elsa-track-super-tease.jpg",
        isInReadList = false
    )

    val cacheHeadLines = HeadLinesCacheEntity(
        id = 1,
        title = "Tropical Storm Elsa weakens and slows, but a tropical storm watch is in effect for South Florida - CNN",
        description = "Tropical Storm Elsa is a little weaker and slower Sunday " +
                "as it swirls away from Haiti toward Jamaica and Cuba, " +
                "according to the National Hurricane Center, but a tropical " +
                "storm watch is in effect for the Florida Keys.",
        url = "https://www.cnn.com/2021/07/04/weather/tropical-storm-elsa-sunday/index.html",
        urlToImage = "https://cdn.cnn.com/cnnnext/dam/assets/210704021246-tropical-storm-elsa-track-super-tease.jpg",
        isInReadList = false
    )

    val callHeadLines_2 = HeadLinesCallEntity(
        source = null,
        author = "Brie Stimson",
        title = "Biden defends Sha'Carri Richardson suspension as progressives, some in GOP balk: ‘Rules are the rules’ - Fox News",
        description = "President Biden gave a muted response Saturday to sprinter Sha’Carri " +
                "Richardson’s suspension due to a positive marijuana test that will cut her " +
                "from the Tokyo Olympics later this month.",
        url = "https://www.foxnews.com/politics/biden-shacarri-richardson-suspension-progressives-republicans-rules-olympics-sprinter",
        urlToImage = "https://static.foxnews.com/foxnews.com/content/uploads/2021/07/biden-richardson.jpg",
        publishedAt = "2021-07-04T06:14:28Z",
        content = "President Biden gave a muted response Saturday to sprinter ShaCarri " +
                "Richardsons suspension due to a positive marijuana test that will cut her " +
                "from the Tokyo Olympics later this month. \\r\\n\\\"The rules ar… [+3263 chars]"
    )

    val headLines_2 = HeadLines(
        title = "Biden defends Sha'Carri Richardson suspension as progressives, some in GOP balk: ‘Rules are the rules’ - Fox News",
        description = "President Biden gave a muted response Saturday to sprinter Sha’Carri " +
                "Richardson’s suspension due to a positive marijuana test that will cut her " +
                "from the Tokyo Olympics later this month.",
        url = "https://www.foxnews.com/politics/biden-shacarri-richardson-suspension-progressives-republicans-rules-olympics-sprinter",
        urlToImage = "https://static.foxnews.com/foxnews.com/content/uploads/2021/07/biden-richardson.jpg",
        isInReadList = false
    )

    val cacheHeadLines_2 = HeadLinesCacheEntity(
        id = 2,
        title = "Biden defends Sha'Carri Richardson suspension as progressives, some in GOP balk: ‘Rules are the rules’ - Fox News",
        description = "President Biden gave a muted response Saturday to sprinter Sha’Carri " +
                "Richardson’s suspension due to a positive marijuana test that will cut her " +
                "from the Tokyo Olympics later this month.",
        url = "https://www.foxnews.com/politics/biden-shacarri-richardson-suspension-progressives-republicans-rules-olympics-sprinter",
        urlToImage = "https://static.foxnews.com/foxnews.com/content/uploads/2021/07/biden-richardson.jpg",
        isInReadList = false
    )


}