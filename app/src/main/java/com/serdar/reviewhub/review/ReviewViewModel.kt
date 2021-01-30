package com.serdar.reviewapp

import android.content.res.AssetManager
import com.google.gson.Gson
import com.serdar.reviewapp.base.BaseViewModel
import com.serdar.reviewhub.entity.ReviewItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream

class ReviewViewModel: BaseViewModel() {

    private var items: ReviewItems? = null

    override fun init() {
        state.value = ReviewState.ReadFromFile("apidemo.json")
    }

    fun readFile(fName: String, assets: AssetManager) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val instream: InputStream = assets.open(fName)
                val size: Int = instream.available()
                val buffer = ByteArray(size)
                instream.read(buffer)
                instream.close()
                val data = buffer.toString(Charsets.UTF_8)
                items = Gson().fromJson<ReviewItems>(data, ReviewItems::class.java)
                items?.let { state.postValue(ReviewState.ListReviews(it.items)) }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

    fun filterByRating(rating: Int) {
        items?.let {
            if (rating == 0) {
                state.value = ReviewState.ListReviews(it.items)
            }
            else {
                state.value = ReviewState.ListReviews(
                        it.items.filter { item -> item.rating == rating }.toList())
            }
        }
    }

    fun clear() {
        state.value = ReviewState.ClearFilter()
        filterByRating(0)
    }
}