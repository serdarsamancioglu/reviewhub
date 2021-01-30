package com.serdar.reviewapp

import com.serdar.reviewhub.entity.ReviewItem
import com.serdar.reviewhub.entity.ReviewItems

sealed class ReviewState: IViewState {
    data class ListReviews(val items: List<ReviewItem>): ReviewState()
    data class ReadFromFile(val fName: String): ReviewState()
    class ClearFilter: ReviewState()
}