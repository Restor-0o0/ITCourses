package com.example.itstepik.util

data class CoursesOrder(
    val dateDesc: String = "-create_data",
    val dateAsc: String = "+create_data",
    val popularDesc: String = "-discussions_count ",
    val popularAsc: String = "+discussions_count ",
)
