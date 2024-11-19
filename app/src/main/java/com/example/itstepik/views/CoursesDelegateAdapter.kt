package com.example.itstepik.views

import android.os.Build
import com.bumptech.glide.Glide
import com.example.itstepik.R
import com.example.itstepik.data.Course
import com.example.itstepik.data.ListItem
import com.example.itstepik.databinding.CourseItemBinding
import com.example.itstepik.util.months
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun courseAdapterDelegate(itemClickedListener : (Course) -> Unit)  =
    adapterDelegateViewBinding<Course, ListItem, CourseItemBinding>(
        { layoutInflater, root -> CourseItemBinding.inflate(layoutInflater, root, false) }
    ){

        bind{
            Glide
                .with(binding.image.context)
                .load(item.cover)
                .into(binding.image)
            binding.title.text = item.title
            if(item.display_price == "-"){
                binding.price.text = "free"
            }else{
                binding.price.text = item.display_price
            }
            binding.summary.text = item.summary
            binding.image.clipToOutline = true
            binding.ratingText.text = item.average.toString()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.date.text = ZonedDateTime.parse(item.create_date).dayOfMonth.toString() + " " + months.get(ZonedDateTime.parse(item.create_date).monthValue) + " " + ZonedDateTime.parse(item.create_date).year.toString()
            } else {

                binding.date.text = Date(item.create_date).day.toString() + " " + months.get(Date(item.create_date).month) +" "+ (Date(item.create_date).year)
            }







        }
    }