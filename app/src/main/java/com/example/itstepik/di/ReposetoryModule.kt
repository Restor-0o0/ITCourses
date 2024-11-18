package com.example.itstepik.di

import android.content.Context
import com.example.itstepik.StepikAPI
import com.example.itstepik.reposetory.CoursesReposetory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ReposetoryMoule = module{
    fun providerReposetory(context: Context,api:StepikAPI):CoursesReposetory{
        return CoursesReposetory(context,api)
    }
    single{
        providerReposetory(androidContext(),get())
    }
}