package com.example.itstepik.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itstepik.data.Course
import com.example.itstepik.data.Response.CoursesResponse
import com.example.itstepik.data.Response.ReviewSummaryResponse
import com.example.itstepik.data.Response.TagsResponse
import com.example.itstepik.data.Tag
import com.example.itstepik.reposetory.CoursesReposetory
import com.example.itstepik.util.APIResult
import com.example.itstepik.util.handleResponse
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

class CoursesViewModel(
    val reposetory: CoursesReposetory
): ViewModel() {

    private var _coursesList =  MutableLiveData<List<Course>>()
    val coursesList: LiveData<List<Course>> get() =  _coursesList
    private var _tagsList =  MutableLiveData<List<Tag>>()
    val tagsList: LiveData<List<Tag>> get() =  _tagsList
    val showError = MutableLiveData<String?>()
    val showLoading = MutableLiveData<Boolean?>()
    var coutCourses:Int = 0
    val parent = 2


    fun loadCourses(tag:Int=1,order: String = ""){
        Log.e("TAGGGGSSS__",tag.toString())
        try{
        viewModelScope.launch {
            Log.e("TAGGGGSSS____",tag.toString())
            showLoading.value = true
            showError.value = null
            _coursesList.value = emptyList()
            reposetory.setOrder(order)
            try{
                val result: APIResult<CoursesResponse> = reposetory.getCoursesByTag(tag)


                when(result){

                    is APIResult.Success-> {
                        _coursesList.value = result.successData.courses
                        Log.e("TAGGGGSSS_______",result.successData.courses.size.toString())
                        for(item in result.successData.courses){
                            loadAverage(item.id)
                        }
                        showLoading.value = null
                        cancel()
                    }
                    is APIResult.Error->{
                        Log.e("TAGGGGSSS_______","")
                        showError.value = result.message
                        showLoading.value = false
                        cancel()
                    }
                }
            }catch (e:Exception){
                Log.e("TAGGGGSSS__________",e.toString())
                Log.e("DEBUUGG",e.toString())
                cancel()
            }
        }
        }catch (e:Exception){
            Log.e("TAGGGGSSS__________",e.toString())
        }

    }
    fun loadNextCourses(){
        viewModelScope.launch {
            showLoading.value = true
            showError.value = null
            try{
                val result: APIResult<CoursesResponse> = reposetory.getNextCourses()
                when(result){
                    is APIResult.Success-> {
                        Log.e("size",_coursesList.value?.size.toString())
                        for(item in result.successData.courses){
                            Log.e("ADDED",item.id.toString())
                            _coursesList.value = _coursesList.value!! + item
                            Log.e("ADDED",item.id.toString()+"___")
                            loadAverage(item.id)
                        }
                        showLoading.value = null
                        cancel()
                    }
                    is APIResult.Error->{
                        showError.value = result.message
                        showLoading.value = false
                        cancel()

                    }
                }
            }catch (e:Exception){
                cancel()
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
                           cancel()
                       }
                       is APIResult.Error ->{
                           cancel()
                       }
                   }
               }catch(e:Exception){
                   cancel()
               }


        }
    }
     fun loadTagsByParent(parent: Int = this.parent){
        viewModelScope.launch {
            _tagsList.value = emptyList()
            try{
                var i = 1
                while(true){
                    val result: APIResult<TagsResponse> = reposetory.getTagsByParent(parent,i)
                    when(result){
                        is APIResult.Success ->{
                            if(_tagsList.value?.size!! > 0){
                                val list:List<Tag> = _tagsList.value!! + result.successData.tags
                                _tagsList.value = list
                                for(tag in result.successData.tags){
                                    _tagsList.value?.plus(tag)
                                    Log.e("TAGGGGSSS",tag.title)
                                }
                            }
                            else{
                              _tagsList.value = result.successData.tags
                            }

                            if(result.successData.meta.next == false){
                                cancel()
                                return@launch
                            }
                        }
                        is APIResult.Error ->{
                            cancel()
                            break
                        }
                    }
                    Log.e("TAGGGGSSS",_tagsList.value!!.size.toString())
                    i+=1
                }

            }catch (e:Exception){
                Log.e("TAGGGGSSS",e.message.toString())
                cancel()
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