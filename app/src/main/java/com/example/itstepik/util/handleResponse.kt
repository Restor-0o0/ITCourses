package com.example.itstepik.util

import retrofit2.Response

object handleResponse {
    fun <T:Any> handleError(response : Response<T>) : APIResult.Error{
        val error = parseErrorr.parse(response)
        return APIResult.Error(Exception(error.message))
    }
    fun <T:Any> handleSuccess(response: Response<T>): APIResult<T>{
        response.body()?.let{
            return APIResult.Success(it)
        }?: return handleError(response)
    }
}