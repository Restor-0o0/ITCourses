package com.example.itstepik.data

import com.google.gson.annotations.SerializedName

data class CoursesBlock(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("courses")
    val courses: List<Int>

)