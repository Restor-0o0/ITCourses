package com.example.itstepik.di

import com.example.itstepik.StepikAPI
import org.koin.dsl.module
import retrofit2.Retrofit


val ApiModule = module{
    fun provideApi(retrofit: Retrofit): StepikAPI{
        return retrofit.create(StepikAPI::class.java    )
    }
    single{
        provideApi(get())
    }
}
