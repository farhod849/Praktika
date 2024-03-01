package com.example.praktos
import android.content.Context
import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktos.databinding.ActivityMainBinding
import com.example.praktos.databinding.PostcardBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),PostAdapter.Listener {
    private val viewModel: PostViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostAdapter(this)
        binding.container.adapter = adapter
<<<<<<< HEAD
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
        viewModel.data.observe(this) {
            adapter.submitList(it)
=======
        viewModel.data.observe(this) { post ->
            adapter.submitList(post)
>>>>>>> ae849e678aa5d671499275a6a11a8db316788e47
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
                viewModel.editContent(text.toString(), binding.linkText.text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        intent?.let{
            if(it. action != Intent.ACTION_SEND){
                return@let
            }
            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if(text.isNullOrBlank()){
                Snackbar.make(binding.root, "Пост не содержит текста!", LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok){
                        finish()
                    }
                    .show()
                return@let
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

    override fun add() {
        binding.linkText.visibility = View.VISIBLE
    }

    override fun onClickLike(post: Post) {
        viewModel.likeById(post.id)
    }
    override fun onClickShare(post: Post) {
        viewModel.shareById(post.id)
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, post.content)
        }
        val shareIntent = Intent.createChooser(intent, "Выберите приложение")
        startActivity(shareIntent)
    }

object AndroidUtils{
    fun hideKeyboard(view: View){
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


}
