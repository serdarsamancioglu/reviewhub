package com.serdar.reviewhub.entity

import com.google.gson.annotations.SerializedName

class ComputedBrowser {
    @SerializedName("Browser")
    var browser: String? = null
    @SerializedName("Version")
    var version: String? = null
    @SerializedName("Platform")
    var platform: String? = null
    @SerializedName("FullBrowser")
    var fullBrowser: String? = null
}