package com.example.itstepik.di

import android.text.format.Time
import com.example.itstepik.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModeule = module{

    val connectTimeout: Long = 20
    fun providerHttpClient(): OkHttpClient{
        val httpCliet = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
        return httpCliet.build()
    }

    fun poviderRetrofit(okHttpClient: OkHttpClient,url:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }
    single{
        providerHttpClient()
    }
    single{
        val url = androidContext().getString(R.string.BASE_URL)
        poviderRetrofit(get(),url)
    }
}