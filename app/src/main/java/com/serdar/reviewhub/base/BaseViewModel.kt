package com.serdar.reviewapp.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serdar.reviewapp.IViewState

abstract class BaseViewModel: ViewModel() {

    val state = MutableLiveData<IViewState>()
    lateinit var context: Context

    abstract fun init()

    fun setApplicationContext(context: Context) {
        this.context = context
    }
}