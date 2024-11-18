package com.example.itstepik.data.Response

import com.example.itstepik.data.Course
import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<Course>
)
