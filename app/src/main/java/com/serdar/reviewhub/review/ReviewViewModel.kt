package com.serdar.reviewapp

import android.content.res.AssetManager
import com.serdar.reviewapp.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream

class ReviewViewModel: BaseViewModel() {

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
                state.postValue(ReviewState.FileRead(buffer.toString(Charsets.UTF_8)))
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }
}