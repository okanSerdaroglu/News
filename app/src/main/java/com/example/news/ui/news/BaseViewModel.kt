package com.example.news.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.util.Event


open class BaseViewModel : ViewModel() {

    private val _mainStateView = MutableLiveData<Event<MainViewState>>()
    val mainStateView: LiveData<Event<MainViewState>> = _mainStateView

    fun setMainStateView(mainViewState: MainViewState) {
        _mainStateView.value = Event(mainViewState)
    }

}