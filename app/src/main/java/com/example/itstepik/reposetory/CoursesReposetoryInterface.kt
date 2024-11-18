package com.example.itstepik.reposetory

import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.util.APIResult

interface CoursesReposetoryInterface{
    fun getAllCourses(): APIResult<CoursesResponse>
    fun getCoursesByTag(tag:Int): APIResult<CoursesResponse>
    fun getNextCourses(): APIResult<CoursesResponse>
    fun orderDateDesc()
    fun orderDateAsc()
    fun orderPopDesc()
    fun orderPopAsc()
    fun clearOrder()

}