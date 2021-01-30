package com.serdar.reviewapp

sealed class SplashState: IViewState {
    class Animation: SplashState()
    class Finish: SplashState()
}