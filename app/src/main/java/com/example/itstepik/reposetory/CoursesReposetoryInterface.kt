package com.example.itstepik.reposetory

import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.data.Response.ReviewSummaryResponse
import com.example.itstepik.data.Response.TagsResponse
import com.example.itstepik.util.APIResult

interface CoursesReposetoryInterface{
    suspend fun getAllCourses(): APIResult<CoursesResponse>
    suspend fun getCoursesByTag(tag:Int): APIResult<CoursesResponse>
    suspend fun getNextCourses(): APIResult<CoursesResponse>
    suspend fun getReviewSummary(corse: Int): APIResult<ReviewSummaryResponse>
    suspend fun getTagsByParent(parent: Int,page: Int): APIResult<TagsResponse>
    fun setOrder(order:String): Boolean

}