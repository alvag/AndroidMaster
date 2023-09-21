package com.maxalva.androidmaster.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.maxalva.androidmaster.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val etName = findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener {
            val name = etName.text.toString()
            if (name.isNotEmpty()) {
                Log.i("FirstAppActivity", "Button clicked $name")

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }
}