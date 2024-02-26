package com.example.praktos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)


}
class PostRepositoryInMemoryImpl : PostRepository{

    private var posts = listOf(
        Post(
            id = 2,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
        ),
        Post(
            id = 1,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
            ),Post(
            id = 3,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
        ),
        Post(
            id = 4,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
        ),Post(
            id = 5,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
        ),
        Post(
            id = 6,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
        ),Post(
            id = 7,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
        ),
        Post(
            id = 8,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
        ),
    )


    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else{
                if (it.likedByMe)
                    it.amountLike--
                else
                    it.amountLike++
                it.copy(likedByMe = !it.likedByMe)
            }
        }
        data.value = posts
    }

    override fun shareById(id: Long){
        posts = posts.map{
            if (it.id != id) it else
                it.copy(amountShare = it.amountShare+1)

        }
        data.value = posts
    }
}
class PostViewModel : ViewModel(){
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
}