package com.example.itstepik.data.Response

import com.example.itstepik.data.Course
import com.google.gson.annotations.SerializedName

class CoursesResponse{
    @SerializedName("courses")
    public lateinit var courses: List<Course>
}
