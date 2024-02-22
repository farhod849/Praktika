package com.example.praktos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.praktos.databinding.ActivityMainBinding

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)


        var button = findViewById<Button>(R.id.button1)
        button.setOnClickListener{
            var myIntent = Intent(this,MainActivity::class.java)
            startActivity(myIntent)
        }

    }
}