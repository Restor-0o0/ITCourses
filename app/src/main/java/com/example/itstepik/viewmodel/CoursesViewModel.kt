package com.example.itstepik.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itstepik.data.Course
import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.data.Response.ReviewSummaryResponse
import com.example.itstepik.reposetory.CoursesReposetory
import com.example.itstepik.util.APIResult
import com.example.itstepik.util.handleResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class CoursesViewModel(
    val reposetory: CoursesReposetory
): ViewModel() {

    private var _coursesList =  MutableLiveData<List<Course>>()
    val coursesList: LiveData<List<Course>> get() =  _coursesList
    private var _addedCourses =  MutableLiveData<List<Course>>()
    val addedCourses: LiveData<List<Course>> get() =  _addedCourses
    val showError = MutableLiveData<String?>()
    val showLoading = MutableLiveData<Boolean?>()
    var coutCourses:Int = 0


    fun loadCourses(tag:Int=1,order: String = ""){
        viewModelScope.launch {
            showLoading.value = true
            showError.value = null
            _coursesList.value = emptyList()
            reposetory.setOrder(order)
            try{
                val result: APIResult<CoursesResponse> = reposetory.getCoursesByTag(tag)


                when(result){

                    is APIResult.Success-> {
                        _coursesList.value = result.successData.courses
                        for(item in result.successData.courses){
                            loadAverage(item.id)
                        }
                        showLoading.value = null

                    }
                    is APIResult.Error->{
                        showError.value = result.message
                        showLoading.value = false

                    }
                }
            }catch (e:Exception){
                Log.e("DEBUUGG",e.toString())
            }
        }


    }
    fun loadNextCourses(){
        viewModelScope.launch {
            showLoading.value = true
            showError.value = null
            _addedCourses.value = emptyList()
            try{
                val result: APIResult<CoursesResponse> = reposetory.getNextCourses()
                when(result){
                    is APIResult.Success-> {
                        _addedCourses.value = result.successData.courses
                        _coursesList.value = _coursesList.value?.plus(_addedCourses.value!!)
                        Log.e("size",_coursesList.value?.size.toString())
                        for(item in result.successData.courses){
                            Log.e("ADDED",item.id.toString())
                            _coursesList.value?.plus(item)
                            Log.e("ADDED",item.id.toString()+"___")
                            loadAverage(item.id)
                        }
                        showLoading.value = null
                    }
                    is APIResult.Error->{
                        showError.value = result.message
                        showLoading.value = false
                    }
                }
            }catch (e:Exception){

            }
        }
    }

    suspend fun loadAverage(course:Int){
        viewModelScope.launch {
               try{
                   val result: APIResult<ReviewSummaryResponse> = reposetory.getReviewSummary(course)
                   when(result){
                       is APIResult.Success ->{
                           for(item in result.successData.reciewSummaryLst){
                               Log.e("AVERAGE1",item.id.toString())
                               setAverage(item.course,item.average)
                           }
                       }
                       is APIResult.Error ->{
                       }
                   }
               }catch(e:Exception){
               }


        }
    }

    private fun setAverage(course: Int,average:Double){
        for(item in _coursesList.value!!){
            if(item.id == course){
                Log.e("AVERAGE2",item.average.toString())
                item.average = average
                return
            }
        }
    }
}