package com.maxalva.androidmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.maxalva.androidmaster.firstapp.FirstAppActivity
import com.maxalva.androidmaster.imccalculator.IMCActivity
import com.maxalva.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludo = findViewById<Button>(R.id.btnSaludo)
        val btnIMC = findViewById<Button>(R.id.btnIMC)
        val btnTodo = findViewById<Button>(R.id.btnTODO)

        btnSaludo.setOnClickListener { navigate(FirstAppActivity::class.java) }
        btnIMC.setOnClickListener { navigate(IMCActivity::class.java) }
        btnTodo.setOnClickListener { navigate(TodoActivity::class.java) }

    }

    private fun navigate(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
}