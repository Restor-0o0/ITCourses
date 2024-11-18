package com.example.itstepik.data

import com.google.gson.annotations.SerializedName

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
) :ListItem
