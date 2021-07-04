package com.example.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.news.R
import com.example.news.util.ErrorType
import com.example.news.util.UiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.mainStateView.observe(this, {
            it?.getContentIfNotHandled()?.let { mainViewState ->
                if (mainViewState.uiStatus is ErrorType) {
                    when (val errorType = (mainViewState.uiStatus as UiStatus.Error).errorType) {
                        is ErrorType.DIALOG -> {
                            // show dialog
                        }
                        is ErrorType.TOAST -> {
                            // show toast
                        }
                        else -> {

                        }
                    }
                }
            }
        })
    }
}