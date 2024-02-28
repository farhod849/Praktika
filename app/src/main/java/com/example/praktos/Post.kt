package com.example.praktos

data class Post(
    var id: Long,
    val author: String,
    val content: String,
    var amountLike: Int,
    val likedByMe: Boolean,
    var amountShare: Int,
<<<<<<< Updated upstream
=======
    var viewAmount: Int
>>>>>>> Stashed changes
)
