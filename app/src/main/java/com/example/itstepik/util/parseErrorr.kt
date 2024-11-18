package com.example.itstepik.util

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Response


object parseErrorr {
    fun parse(response: Response<*>) : APIError{
        val gson = GsonBuilder().create()
        val error : APIError
        try {
            error = gson.fromJson(response.body()?.toString(),APIError::class.java)
        }catch (e: Exception){
            e.message?.let{ Log.e("parseError",it)}
            return APIError()
        }
        return error
    }
}