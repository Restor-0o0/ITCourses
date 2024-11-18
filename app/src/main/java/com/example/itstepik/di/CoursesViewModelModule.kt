package com.example.itstepik.di

import com.example.itstepik.reposetory.CoursesReposetory
import com.example.itstepik.viewmodel.CoursesViewModel
import org.koin.dsl.module

val ViewModelModule = module{

    fun providerViewModel(reposetory: CoursesReposetory): CoursesViewModel{
        return CoursesViewModel(reposetory)
    }
    single{
        providerViewModel(get())
    }
}