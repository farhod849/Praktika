package com.example.praktos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}
class PostRepositoryInMemoryImpl : PostRepository{
    public var post = Post(
        id = 1,
        author = "Дискорд это бесплатный мессенджер для геймеров!",
        content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
        amountLike = 999,
        amountShare = 999,
        likedByMe = false
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like(){
        if (!post.likedByMe) {
            post.amountLike++
        } else {
            post.amountLike--
        }
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }
    override fun share(){
            post.amountShare++
        data.value = post
    }
}
class PostViewModel : ViewModel(){
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}