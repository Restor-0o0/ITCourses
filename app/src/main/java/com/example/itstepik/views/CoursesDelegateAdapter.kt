package com.example.itstepik.views

import com.example.itstepik.R
import com.example.itstepik.data.Course
import com.example.itstepik.data.ListItem
import com.example.itstepik.databinding.CourseItemBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseAdapterDelegate(itemClickedListener : (Course) -> Unit)  =
    adapterDelegateViewBinding<Course, ListItem, CourseItemBinding>(
        { layoutInflater, root -> CourseItemBinding.inflate(layoutInflater, root, false) }){


    }