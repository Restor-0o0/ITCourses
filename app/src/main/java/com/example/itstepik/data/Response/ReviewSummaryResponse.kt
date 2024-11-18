package com.example.itstepik.data.Response

import com.example.itstepik.data.ReviewSummary
import com.google.gson.annotations.SerializedName

class ReviewSummaryResponse {
    @SerializedName("course-review-summaries")
    lateinit var reciewSummaryLst: List<ReviewSummary>
}