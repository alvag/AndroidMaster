package com.maxalva.androidmaster.firstapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.maxalva.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val name = intent.getStringExtra("name").orEmpty()

        textViewResult.text = "Hello $name"
    }
}