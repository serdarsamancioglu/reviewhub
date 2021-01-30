package com.serdar.reviewhub.entity

import com.google.gson.annotations.SerializedName

class ReviewItem {
    @SerializedName("computed_browser")
    var computedBrowser: ComputedBrowser? = null
    @SerializedName("computed_location")
    var computedLocation: String? = null
    var geo: Geo? = null
    var labels: List<String>? = null
    var rating: Int? = 0
    var comment: String? = null
}