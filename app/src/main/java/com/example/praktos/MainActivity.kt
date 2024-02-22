package com.example.praktos

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var present_value_int = 999
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){post ->
            with(binding){
                textView3.text = post.author
                textViewmain.text = post.content
                imageButton2.setBackgroundResource(if(post.likedByMe) R.drawable.like_press else R.drawable.like_unpress)
                textViewAmountLike.text = toStringFromNumb(post.amountLike)
                textView6.text = toStringFromNumb(post.amountShare)
            }
        }
        binding.imageButton2.setOnClickListener {
            viewModel.like()
        }
        binding.imageButton3.setOnClickListener {
            viewModel.share()
        }

    }
    fun toStringFromNumb(count:Int):String
    {
        return when(count){
            in 0..<1_000 -> count.toString()
            in 1000..<1_100-> "1K"
            in 1_100..<10_000 -> ((count/100).toFloat()/10).toString() + "K"
            in 10_000..<1_000_000 -> (count/1_000).toString() + "K"
            in 1_000_000..<1_100_000 -> "1M"
            in 1_100_000..<10_000_000 -> ((count/100_000).toFloat()/10).toString() + "M"
            in 10_000_000..<1_000_000_000 -> (count/1_000_000).toString() + "M"
            else ->  "более млрд"
        }
    }

}