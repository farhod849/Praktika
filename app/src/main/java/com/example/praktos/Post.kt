package com.example.praktos

data class Post(

    var id: Long,
    val author: String,
    val content: String,
    var amountLike: Int,
    val likedByMe: Boolean,
    var amountShare: Int,
    var viewAmount: Int,
    val link: String,
)
