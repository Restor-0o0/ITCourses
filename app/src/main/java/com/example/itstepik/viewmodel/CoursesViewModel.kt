package com.example.itstepik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itstepik.data.Course
import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.reposetory.CoursesReposetory
import com.example.itstepik.util.APIResult
import kotlinx.coroutines.launch
import retrofit2.Response

class CoursesViewModel(
    val reposetory: CoursesReposetory
): ViewModel() {

    var _coursesList =  MutableLiveData<List<Course>>()
    val coursesList: LiveData<List<Course>> get() =  _coursesList
    lateinit var showError: MutableLiveData<String?>
    lateinit var showLoading: MutableLiveData<Boolean?>


    fun loadCourses(tag:Int=1,order: String){
        viewModelScope.launch {
            showLoading.value = true
            showError.value = null
            try{
                val result: APIResult<CoursesResponse>
                if(tag == 1){
                    result = reposetory.getAllCourses()
                }
                else{
                    result = reposetory.getCoursesByTag(tag)
                }
                when(result){
                    is APIResult.Success-> {
                        _coursesList.value = result.successData.courses
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
}