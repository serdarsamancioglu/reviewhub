package com.serdar.reviewhub.splash

import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.view.View
import com.serdar.reviewapp.IViewState
import com.serdar.reviewapp.SplashState
import com.serdar.reviewapp.base.BaseActivity
import com.serdar.reviewhub.R
import com.serdar.reviewhub.databinding.ActivitySplashBinding
import com.serdar.reviewhub.review.ReviewActivity

class SplashActivity: BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    lateinit var mediaPlayer: MediaPlayer

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel() = SplashViewModel::class.java

    override fun onStateChange(state: IViewState) {
        when (state) {
            is SplashState.Animation -> {
                val resId = resources.getIdentifier("whoosh", "raw", packageName)
                mediaPlayer = MediaPlayer.create(this, resId)
                startAnimation()
            }
            is SplashState.Finish -> {
                val intent = Intent(this, ReviewActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun startAnimation() {
        animateView(binding.star1, 500)
        animateView(binding.star2, 1000)
        animateView(binding.star3, 1500)
        animateView(binding.star4, 2000)
        animateView(binding.star5, 2500)
        animateView(binding.title, 3000)
        viewModel.finish()
    }

    private fun animateView(v: View, delay: Long) {

        v.animate().translationXBy(1000f)
        Handler(Looper.getMainLooper()).postDelayed({
            playSound()
            v.animate().alpha(1f).translationXBy(-1000f).setDuration(500)
        }, delay)
    }

    private fun playSound() {
        mediaPlayer.seekTo(0)
        mediaPlayer.start()
    }
}