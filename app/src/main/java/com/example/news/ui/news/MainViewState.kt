package com.example.news.ui.news

import com.example.news.util.UiStatus

data class MainViewState(
    val uiStatus: UiStatus
) {
    val isLoading = uiStatus is UiStatus.Loading
    val isError = uiStatus is UiStatus.Error
}