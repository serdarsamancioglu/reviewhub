package com.serdar.reviewapp

import com.serdar.reviewapp.base.BaseViewModel

class ReviewViewModel: BaseViewModel() {

    override fun init() {
        state.value = ReviewState.ReadFromFile("apidemo.json")
    }
}