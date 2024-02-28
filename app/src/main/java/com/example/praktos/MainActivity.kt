package com.example.praktos
import android.content.Context
import android.os.Bundle
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktos.databinding.ActivityMainBinding
import com.example.praktos.databinding.PostcardBinding
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
class MainActivity : AppCompatActivity(),PostAdapter.Listener {
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< Updated upstream
<<<<<<< Updated upstream

        val adapter = PostAdapter(this)
        binding.container.adapter = adapter
        viewModel.data.observe(this){posts ->
            adapter.list = posts
        }


    }
    override fun onClickLike(post: Post) {
        viewModel.likeById(post.id)
    }
    override fun onClickShare(post: Post) {
        viewModel.shareById(post.id)
    }
}
=======
=======
>>>>>>> Stashed changes
        val adapter = PostAdapter(this)
        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
        binding.container.adapter = adapter
        viewModel.data.observe(this) { post ->
            adapter.submitList(post)
        }
        binding.sendMessage.setOnClickListener{
            with(binding.messageText){
                if(text.isNullOrBlank()){
                    Toast.makeText(
                        this@MainActivity,
                        "Поле не может быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.editContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
    override fun onClickMore(post: Post,view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu_more, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            if (it.itemId == R.id.popup_delete) {
                viewModel.removeById(post.id)
            }
            true
        }
        popupMenu.show()
    }
    override fun onEdit(post: Post){
        viewModel.edit(post)
    }
    override fun onLike(post: Post){
        viewModel.likeById(post.id)
    }
    override fun onRemove(post: Post){
        viewModel.removeById(post.id)
    }
    override fun onClickLike(post: Post) {
        viewModel.likeById(post.id)
    }
    override fun onClickShare(post: Post) {
        viewModel.shareById(post.id)
    }
object AndroidUtils{
    fun hideKeyboard(view: View){
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
}
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
