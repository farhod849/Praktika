package com.example.praktos

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var present_value_int = 980
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){post ->
            with(binding){
                textView3.text = post.author
                textViewmain.text = post.content
                imageButton2.setImageResource(
                    if(post.likedByMe) R.drawable.liked else R.drawable.likes
                )
            }
        }
        binding.imageButton2.setOnClickListener {
            viewModel.like()
        }

        setContentView(R.layout.activity_main)
        var imgbtn = findViewById<ImageButton>(R.id.imageButton2)
        var text2 = findViewById<TextView>(R.id.textView5)
        var count = true;


        imgbtn.setOnClickListener{

                    if (count)
                    {
                        text2.setText("1")
                        imgbtn.setBackgroundResource(R.drawable.liked)
                    }
                    else
                    {

                        text2.setText("0")
                        imgbtn.setBackgroundResource(R.drawable.likes)
                    }
                    count = count.not()
                }





        var text1 = findViewById<TextView>(R.id.textView6)
        var imgbtn1 = findViewById<ImageButton>(R.id.imageButton3)
        imgbtn1.setOnClickListener {
            present_value_int+=1;
            text1.setText(likescount(present_value_int));
        }
    }
    fun likescount(count:Int):String
    {
        return when(count)
        {
            in 0..999 -> count.toString()
            in 1000..<10000000 -> ((count/100).toFloat()/10).toString() + "К"
            in 1000000..1000000000 -> ((count/100000).toFloat()/10).toString() + "М"
            else -> "Более млрд"
        }
    }

}