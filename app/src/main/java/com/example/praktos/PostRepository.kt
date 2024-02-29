package com.example.praktos

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.combine
import kotlin.random.Random

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)
    fun editContent(content: String)
}
class PostRepositoryInMemoryImpl : PostRepository{
    private var posts = listOf(
        Post(
            id = 2,
            author = "Discord, бесплатеный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        ),
        Post(
            id = 1,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
            ),Post(
            id = 3,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        ),
        Post(
            id = 4,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        ),Post(
            id = 5,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        ),
        Post(
            id = 6,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        ),Post(
            id = 7,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        ),
        Post(
            id = 8,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000)
        )
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
    override fun removeById(id: Long) {
        posts = posts.filter { it.id!= id }
        data.value = posts
    }
    override fun save(post: Post){
        if (post.id == 0L){
            posts = listOf(post.copy(
                id = nextId(posts),
                author = "Фарход Сабиров",
                likedByMe = false,
            )) + posts
            data.value = posts
            return
        }
        posts = posts.map{
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

    override fun editContent(content: String) {

    }

    fun nextId(posts:List<Post>):Long
    {
        var id = 1
        posts.forEach{it1->
            posts.forEach{
                if (it.id.toInt() ==id) id= (it.id+1).toInt()
            }
        }

        return id.toLong()
    }
}
private val empty = Post (
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    amountShare = 0,
    amountLike = 0,
    viewAmount = Random.nextInt(1,200000)
)
class PostViewModel : ViewModel(){
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save(){
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun editContent(content: String){
        edited.value?.let {
            val text = content.trim()
            if (edited.value?.content == text){
                return
            }
            edited.value = edited.value?.copy(content = text)
        }
    }
    fun edit(post: Post){
        edited.value = post
    }
    fun likeById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun shareById(id: Long) = repository.shareById(id)
}