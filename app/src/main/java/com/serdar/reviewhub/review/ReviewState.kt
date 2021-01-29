package com.serdar.reviewapp

sealed class ReviewState: IViewState {
    data class FileRead(val data: String): ReviewState()
    data class ReadFromFile(val fName: String): ReviewState()
}