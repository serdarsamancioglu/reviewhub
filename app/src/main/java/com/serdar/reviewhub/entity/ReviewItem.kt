package com.serdar.reviewapp.entity

import com.google.gson.annotations.SerializedName

class ReviewItem {
    @SerializedName("computed_browser")
    lateinit var computedBrowser: ComputedBrowser
    @SerializedName("computed_location")
    lateinit var computedLocation: String
    lateinit var geo: Geo
    lateinit var labels: List<String>
    var rating: Int? = 0
}