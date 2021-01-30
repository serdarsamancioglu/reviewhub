package com.serdar.reviewhub.splash

import android.os.Handler
import android.os.Looper
import com.serdar.reviewapp.SplashState
import com.serdar.reviewapp.base.BaseViewModel

class SplashViewModel: BaseViewModel() {

    override fun init() {
        state.value = SplashState.Animation()
    }

    fun finish() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                state.value = SplashState.Finish()
            }, 5000)
        }

    }
}