package com.example.praktos

import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktos.databinding.ActivityMainBinding
import com.example.praktos.databinding.PostcardBinding

class MainActivity : AppCompatActivity(),PostAdapter.Listener {
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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