package com.example.praktos

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
class PostRepositoryInMemoryImpl(private var context: Context): PostRepository{
    private val gson = Gson()
    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val key = "posts"
    private var nxtId = 1L
    //private var posts = emptyList<Post>()
    private var posts = listOf(
<<<<<<< HEAD
        Post(
            id = 1,
            author = "Discord, бесплатный чат для геймеров!",
            content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",
            likedByMe = false,
            amountShare = 999,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000),
            link = "https://www.youtube.com/watch?v=KfBwSlhemb0"
        ),
        Post(
            id = 2,
            author = "ВК - удобный мессенджер для телефонов",
            content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",
            amountShare = 999,
            likedByMe = false,
            amountLike =  999,
            viewAmount = Random.nextInt(1,200000),
            link="https://youtu.be/LfNzk_fwvH4?si=GPiQPpMXszsQYBFi"
=======
         Post(

             id = 2,

             author = "Discord, бесплатеный чат для геймеров!",

             content = "Discord — это бесплатный мессенджер, который позволяет вам обмениваться голосовым, видео и текстовым чатом с друзьями, игровыми сообществами и разработчиками. У него сотни миллионов пользователей, что делает его одним из самых популярных способов общения с людьми в Интернете. Discord можно использовать практически на всех популярных платформах и устройствах, включая Windows, macOS, Linux, iOS, iPadOS, Android, а также в веб-браузерах.",

             likedByMe = false,

             amountShare = 999,

             amountLike =  999,

             viewAmount = Random.nextInt(1,200000),

             link = "https://www.youtube.com/watch?v=wzNAQMPJPj0&t=3s"


         ),

         Post(

             id = 1,

             author = "ВК - удобный мессенджер для телефонов",

             content = "«ВКонтакте» — российская социальная сеть со штаб-квартирой в Санкт-Петербурге.",

             amountShare = 999,

             likedByMe = false,

             amountLike =  999,

             viewAmount = Random.nextInt(1,200000),

             link = "https://www.youtube.com/watch?v=LfNzk_fwvH4&t=3s"

         )

>>>>>>> ae849e678aa5d671499275a6a11a8db316788e47
        )
    init{
        prefs.getString(key, null)?.let {
            posts = gson.fromJson(it, type)
           //data.value = posts
        }
    }
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
        sync()
    }
    override fun shareById(id: Long){
        posts = posts.map{
            if (it.id != id) it else
                it.copy(amountShare = it.amountShare+1)
        }
        data.value = posts
        sync()
    }
    override fun removeById(id: Long) {
        posts = posts.filter { it.id!= id }
        data.value = posts
        sync()
    }
    override fun save(post: Post){
        if (post.id == 0L){
            posts = listOf(post.copy(
                id = nextId(posts),
                author = "Фарход Сабиров",
                likedByMe = false,
            )) + posts
            data.value = posts
            sync()
            return
        }
        posts = posts.map{
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
        sync()
    }
<<<<<<< HEAD
=======
    private fun sync(){
       with(prefs.edit()){
           putString(key, gson.toJson(posts))
           apply()
       }
    }


>>>>>>> ae849e678aa5d671499275a6a11a8db316788e47
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
    viewAmount = Random.nextInt(1,200000),
    link = ""
)
class PostViewModel(application: Application) : AndroidViewModel(application){
    private val repository: PostRepository = PostRepositoryInMemoryImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save(){
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun editContent(content: String, link:String){
        edited.value?.let {
            val text = content.trim()
            if (edited.value?.content == text){
                return
            }
            edited.value = edited.value?.copy(content = text, link = link)
        }
    }
    fun edit(post: Post){
        edited.value = post
    }
    fun likeById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun shareById(id: Long) = repository.shareById(id)
}
class PostRepostitoryFileImpl(    private val context: Context) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"
    private var nextId = 1L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)
    private fun sync(){
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        } else {
            sync()
        }
    }

    override fun getAll(): LiveData<List<Post>> {
        TODO("Not yet implemented")
    }

    override fun likeById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun shareById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun removeById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun save(post: Post) {
        TODO("Not yet implemented")
    }

    override fun editContent(content: String) {
        TODO("Not yet implemented")
    }
}
