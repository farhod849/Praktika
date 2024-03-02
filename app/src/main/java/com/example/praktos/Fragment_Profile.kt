package com.example.praktos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.example.praktos.databinding.FragmentProfileBinding

class Fragment_Profile : Fragment(),PostAdapter.Listener {
    var posts = emptyList<Post>()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileFragment = this
        val adapter = PostAdapter(this)
        binding.RecyclerPosts.adapter = adapter
        postsFragment.viewModel.data.observe(viewLifecycleOwner) { post ->
            adapter.submitList(post.filter{it.author == "Фарход Сабиров"})
        }
    }
    override fun onClickMore(post: Post,view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu_more, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            if (it.itemId == R.id.popup_delete) {
                postsFragment.viewModel.removeById(post.id)
            }
            true
        }
        popupMenu.show()
    }
    override fun onEdit(post: Post){
        postsFragment.viewModel.edit(post)
    }
    override fun onLike(post: Post){
        postsFragment.viewModel.likeById(post.id)
    }
    override fun onRemove(post: Post){
        postsFragment.viewModel.removeById(post.id)
    }

    override fun add() {
        //binding.linkText.visibility = View.VISIBLE
    }

    override fun onClickLike(post: Post) {
        postsFragment.viewModel.likeById(post.id)
    }
    override fun onClickShare(post: Post) {
        postsFragment.viewModel.shareById(post.id)
        val intent = Intent().apply{
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, post.content)
        }
        val shareIntent = Intent.createChooser(intent, "Выберите приложение")
        startActivity(shareIntent)
    }

}
