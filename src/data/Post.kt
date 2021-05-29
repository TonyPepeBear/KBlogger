package com.tonypepe.data

data class Post(
    val title: String,
    val preview: String,
    val date: String,
    val htmlContent: String,
    var id: String = ""
)
