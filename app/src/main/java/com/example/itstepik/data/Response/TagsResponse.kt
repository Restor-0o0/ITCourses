package com.example.itstepik.data.Response

import com.example.itstepik.data.Tag
import com.google.gson.annotations.SerializedName

data class TagsResponse(
    @SerializedName("tags")
    val tags: List<Tag>
)