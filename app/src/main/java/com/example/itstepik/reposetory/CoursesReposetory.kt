package com.example.itstepik.reposetory

import android.content.Context
import android.util.Log
import com.example.itstepik.StepikAPI
import com.example.itstepik.data.Course
import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.data.Response.ReviewSummaryResponse
import com.example.itstepik.util.APIError
import com.example.itstepik.util.APIResult
import com.example.itstepik.util.CoursesOrder
import com.example.itstepik.util.NetworkManager
import com.example.itstepik.util.handleResponse
import retrofit2.Response
import kotlin.reflect.full.memberProperties

class CoursesReposetory(
    val context: Context,
    val api: StepikAPI

    ) : CoursesReposetoryInterface {

        private var page: Int = 1
        private var tag: Int = 1
        private var order: String? = null

    override suspend fun getAllCourses(): APIResult<CoursesResponse> {
        tag = 1 // Teg IT
        page = 1 //First page
        return getCourses()
    }
    override suspend fun getCoursesByTag(tag:Int): APIResult<CoursesResponse> {
        this.tag = tag // selected tag(c/c++,python,rust...)
        page = 1
        return getCourses()
    }

    override suspend fun getNextCourses():APIResult<CoursesResponse> {
        page += 1
        return getCourses()
    }

    override suspend fun getReviewSummary(course: Int): APIResult<ReviewSummaryResponse> {
        if (NetworkManager.isOnline(context)){
            return try {
                    val response: Response<ReviewSummaryResponse> = this.api.getReviewSummary(course)
                    if (response.isSuccessful){
                        handleResponse.handleSuccess(response)
                    }
                    else{
                        handleResponse.handleError(response)
                    }
            }catch(e:Exception){
                APIResult.Error(Exception(e.message))
            }
        }
        else{
            return APIResult.Error(Exception("Ошибка подклюения сети"))
        }
    }

    override fun setOrder(order: String): Boolean {
        val membs = CoursesOrder::class.memberProperties
        if(membs.any { it -> it.get(CoursesOrder()) == order }){
            this.order = order
            return true
        }
        return false
    }


    private suspend fun getCourses(): APIResult<CoursesResponse> {
        if (NetworkManager.isOnline(context)){
            return try {

                if(order == null){
                    Log.e("DEBUUGG","do")
                    val response: Response<CoursesResponse> = this.api.coursesGetByTagPage(tag,page)
                    Log.e("DEBUUGG","posle")
                    if (response.isSuccessful){
                        handleResponse.handleSuccess(response)
                    }
                    else{
                        handleResponse.handleError(response)
                    }
                }else{
                    var response: Response<CoursesResponse> = this.api.coursesGetByTagPageOrder(tag,page,order!!)
                    if (response.isSuccessful){
                        handleResponse.handleSuccess(response)
                    }
                    else{
                        handleResponse.handleError(response)
                    }
                }


            }catch(e:Exception){
                APIResult.Error(Exception(e.message))
            }
        }
        else{
            return APIResult.Error(Exception("Ошибка подклюения сети"))
        }
    }
}