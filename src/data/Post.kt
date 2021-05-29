package com.tonypepe.data

data class Post(
    val title: String,
    val preview: String,
    val date: String,
    val htmlContent: String,
    val tags: List<String>,
    var id: String = ""
)
