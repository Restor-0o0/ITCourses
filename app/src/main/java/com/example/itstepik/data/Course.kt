package com.example.itstepik.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.Date

data class Course(
    @SerializedName("id")
    val id:Int,
    @SerializedName("summary")
    val summary:String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("display_price")
    val display_price: String,
    @SerializedName("create_date")
    val create_date: String,
    var average: Double = 1.0
) :ListItem
