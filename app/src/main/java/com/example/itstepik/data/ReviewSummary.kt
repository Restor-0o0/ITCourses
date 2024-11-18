package com.example.itstepik.data

import com.google.gson.annotations.SerializedName

data class ReviewSummary (
        @SerializedName("id")
        val id: Int,
        @SerializedName("course")
        val course: Int,
        @SerializedName("average")
        val average: Double,
        @SerializedName("count")
        val count: Int
)