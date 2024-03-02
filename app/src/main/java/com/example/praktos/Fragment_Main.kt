package com.example.praktos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.praktos.databinding.ActivityMainBinding
import com.example.praktos.databinding.FragmentMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class Fragment_Main : Fragment(),PostAdapter.Listener{
    lateinit var binding: FragmentMainBinding
    private val viewModel: PostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMainBinding.inflate(layoutInflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PostAdapter(this)
        binding.container.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { post ->
            adapter.submitList(post)
        }
        binding.sendMessage.setOnClickListener{
            with(binding.messageText){
                if(text.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        "Поле не может быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.editContent(text.toString(), binding.linkText.text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                MainActivity.AndroidUtils.hideKeyboard(this)
            }
        }
            mainActivity.intent?.let{
            if(it. action != Intent.ACTION_SEND){
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if(text.isNullOrBlank()){
                Snackbar.make(binding.root, "Пост не содержит текста!",
                    BaseTransientBottomBar.LENGTH_INDEFINITE
                )
                    .setAction(android.R.string.ok){
                        com.example.praktos.mainActivity.finish()
                    }
                    .show()
                return@let
            }
        }
    }
    override fun onClickMore(post: Post,view: View) {
        val popupMenu = PopupMenu(context, view)
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

}