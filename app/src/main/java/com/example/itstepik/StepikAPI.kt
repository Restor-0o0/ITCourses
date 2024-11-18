package com.example.itstepik

import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.data.Response.TagsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StepikAPI {
    @GET("courses/")
    fun coursesGetByTagPage(
        @Query("tag") tag: Int,
        @Query("page") page:Int) : Response<CoursesResponse>
    @GET("courses/")
    fun coursesGetByTagPageOrder(
        @Query("tag") tag: Int,
        @Query("page") page:Int,
        @Query("order") order:String) : Response<CoursesResponse>
    @GET("courses/")
    fun tagGetByParentPage(
        @Query("parent") parent: Int,
        @Query("page") page:Int) : Response<TagsResponse>


}