package com.example.praktos

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    var amountLike: Int,
    val likedByMe: Boolean,
    var amountShare: Int,
)
