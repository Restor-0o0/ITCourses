package com.example.itstepik.data

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
)
