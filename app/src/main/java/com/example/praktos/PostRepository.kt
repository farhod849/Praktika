package com.example.praktos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
}
class PostRepositoryInMemoryImpl : PostRepository{
    public var post = Post(
        id = 1,
        author = "Дискорд это бесплатный мессенджер для геймеров!",
        content = "Дискорд - это бесплатный чат",
        likedByMe = false
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like(){
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }
}
class PostViewModel : ViewModel(){
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
}