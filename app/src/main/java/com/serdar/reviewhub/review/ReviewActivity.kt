package com.serdar.reviewhub.review

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.serdar.reviewapp.IViewState
import com.serdar.reviewapp.ReviewState
import com.serdar.reviewapp.ReviewViewModel
import com.serdar.reviewapp.base.BaseActivity
import com.serdar.reviewhub.MapsActivity
import com.serdar.reviewhub.R
import com.serdar.reviewhub.databinding.ActivityReviewsBinding
import com.serdar.reviewhub.entity.ReviewItems

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
                viewModel.readFile(state.fName, assets)
            }
            is ReviewState.FileRead -> {
                listItems(state.data)
            }
        }
    }

    private fun listItems(data: String) {
        var items = Gson().fromJson<ReviewItems>(data, ReviewItems::class.java)
        val listener = object: ItemClickListener {
            override fun onItemClicked(lat: Double, long: Double) {
                showMap(lat, long)
            }
        }

        val reviewAdapter = ReviewAdapter(items, listener)
        binding.reviewRecyclerView.adapter = reviewAdapter
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun showMap(lat: Double, lon: Double) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("lat", lat)
        intent.putExtra("lon", lon)
        startActivity(intent)
    }
}