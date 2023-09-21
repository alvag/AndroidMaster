package com.maxalva.androidmaster.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.maxalva.androidmaster.R
import com.maxalva.androidmaster.imccalculator.IMCActivity.Companion.IMC_RESULT

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result = intent.getDoubleExtra(IMC_RESULT, 0.0)

        initComponents()
        initListeners()
        initUI(result)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()

        when (result) {
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.underweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_underweight))
                tvDescription.text = getString(R.string.underweight_description)
            }

            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_normal))
                tvDescription.text = getString(R.string.normal_description)
            }

            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_overweight))
                tvDescription.text = getString(R.string.overweight_description)
            }

            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_obesity))
                tvDescription.text = getString(R.string.obesity_description)
            }

            else -> {
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.weight_obesity))
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}