package com.example.itstepik.reposetory

import android.content.Context
import com.example.itstepik.StepikAPI
import com.example.itstepik.data.Course
import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.util.APIError
import com.example.itstepik.util.APIResult
import com.example.itstepik.util.CoursesOrder
import com.example.itstepik.util.NetworkManager
import com.example.itstepik.util.handleResponse
import retrofit2.Response

class CoursesReposetory(
    val context: Context,
    val api: StepikAPI

    ) : CoursesReposetoryInterface {

        private var page: Int = 1
        private var tag: Int = 1
        private var order: String? = null

    override fun getAllCourses(): APIResult<CoursesResponse> {
        tag = 1 // Teg IT
        page = 1 //First page
        return getCourses()
    }
    override fun getCoursesByTag(tag:Int): APIResult<CoursesResponse> {
        this.tag = tag // selected tag(c/c++,python,rust...)
        page = 1
        return getCourses()
    }

    override fun getNextCourses():APIResult<CoursesResponse> {
        page += 1
        return getCourses()
    }

    override fun orderDateDesc() {
        order = CoursesOrder().dateDesc
    }

    override fun orderDateAsc() {
        order = CoursesOrder().dateAsc
    }

    override fun orderPopDesc() {
        order = CoursesOrder().popularDesc
    }

    override fun orderPopAsc() {
        order = CoursesOrder().popularAsc
    }

    override fun clearOrder() {
        order = null
    }


    private fun getCourses(): APIResult<CoursesResponse> {
        if (NetworkManager.isOnline(context)){
            return try {
                lateinit var response: Response<CoursesResponse>
                order?.let{
                    response = this.api.coursesGetByTagPageOrder(tag,page,it)
                }?: {
                    response = this.api.coursesGetByTagPage(tag,page)
                }
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
}