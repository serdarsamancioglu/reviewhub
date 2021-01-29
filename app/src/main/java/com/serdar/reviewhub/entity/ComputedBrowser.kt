package com.serdar.reviewapp.entity

import com.google.gson.annotations.SerializedName

class ComputedBrowser {
    @SerializedName("Browser")
    lateinit var browser: String
    @SerializedName("Version")
    lateinit var version: String
    @SerializedName("Platform")
    lateinit var platform: String
    @SerializedName("FullBrowser")
    lateinit var fullBrowser: String
}