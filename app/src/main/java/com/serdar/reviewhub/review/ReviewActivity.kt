package com.serdar.reviewhub.review

import com.google.gson.Gson
import com.serdar.reviewapp.IViewState
import com.serdar.reviewapp.ReviewState
import com.serdar.reviewapp.ReviewViewModel
import com.serdar.reviewapp.base.BaseActivity
import com.serdar.reviewhub.R
import com.serdar.reviewhub.databinding.ActivityReviewsBinding
import com.serdar.reviewhub.entity.ReviewItems
import java.io.IOException
import java.io.InputStream

class ReviewActivity: BaseActivity<ReviewViewModel, ActivityReviewsBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_reviews
    }

    override fun getViewModel(): Class<ReviewViewModel> {
        return ReviewViewModel::class.java
    }

    override fun onStateChange(state: IViewState) {
        when (state) {
            is ReviewState.ReadFromFile -> {
                readFile(state.fName)
            }
            is ReviewState.FileRead -> {

            }
        }
    }

    private fun readFile(fName: String) {
        try {
            val instream: InputStream = assets.open(fName)
            val size: Int = instream.available()
            val buffer = ByteArray(size)
            instream.read(buffer)
            instream.close()
            listItems(buffer.toString(Charsets.UTF_8))
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private fun listItems(data: String) {
        var items = Gson().fromJson<ReviewItems>(data, ReviewItems::class.java)

    }
}