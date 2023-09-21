package com.maxalva.androidmaster.imccalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.maxalva.androidmaster.R
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rangeSlider: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    private var isMaleViewSelected = true
    private var weight = 60
    private var age = 30
    private var height = 120

    companion object {
        const val IMC_RESULT = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)

        initComponents()
        initListeners()
        initUi()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rangeSlider = findViewById(R.id.rangeSlider)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    private fun initUi() {
        tvHeight.text = "120 cm"
        tvWeight.text = weight.toString()
        tvAge.text = age.toString()
    }

    private fun initListeners() {
        viewMale.setOnClickListener { setGenderColor(true) }

        viewFemale.setOnClickListener { setGenderColor(false) }

        rangeSlider.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            height = df.format(value).toInt()
            tvHeight.text = "$height cm"
        }

        btnSubtractWeight.setOnClickListener {
            if (weight <= 0) return@setOnClickListener

            weight--
            tvWeight.text = weight.toString()
        }

        btnAddWeight.setOnClickListener {
            weight++
            tvWeight.text = weight.toString()
        }

        btnSubtractAge.setOnClickListener {
            if (age <= 0) return@setOnClickListener

            age--
            tvAge.text = age.toString()
        }

        btnAddAge.setOnClickListener {
            Log.i("IMC", "Add Age")
            age++
            tvAge.text = age.toString()
        }

        btnCalculate.setOnClickListener { calculateIMC() }
    }

    private fun calculateIMC() {
        val df = DecimalFormat("#.##")
        val imc = weight / (height.toDouble() / 100 * height.toDouble() / 100)
        val result = df.format(imc).toDouble()
        navigateToResult(result)
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_RESULT, result)
        startActivity(intent)
    }

    private fun setGenderColor(isMaleViewSelected: Boolean) {
        this.isMaleViewSelected = isMaleViewSelected
        var maleColor = R.color.background_component_selected
        var femaleColor = R.color.background_component

        if (!isMaleViewSelected) {
            maleColor = R.color.background_component
            femaleColor = R.color.background_component_selected
        }

        viewMale.setCardBackgroundColor(ContextCompat.getColor(this, maleColor))
        viewFemale.setCardBackgroundColor(ContextCompat.getColor(this, femaleColor))
    }
}