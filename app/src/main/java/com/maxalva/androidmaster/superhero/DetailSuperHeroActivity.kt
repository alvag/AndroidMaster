package com.maxalva.androidmaster.superhero

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.maxalva.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val SUPER_HERO_ID = "super_hero_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(SUPER_HERO_ID).orEmpty()
        getSuperHeroDetails(id)
    }

    private fun getSuperHeroDetails(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(ApiService::class.java).getSuperHeroDetails(id)
            if (response.isSuccessful) {
                val superHeroDetail = response.body()
                if (superHeroDetail != null) {
                    runOnUiThread {
                        createUI(superHeroDetail)
                    }
                }
            }
        }
    }

    private fun createUI(superHeroDetail: SuperHeroDetailResponse) {
        Picasso.get()
            .load(superHeroDetail.image.url)
            .into(binding.ivSuperHero)

        binding.tvSuperHeroName.text = superHeroDetail.name

        prepareStats(superHeroDetail.powerStats)
    }

    private fun prepareStats(powerStats: PowerStats) {
        updateHeight(binding.vDurability, powerStats.durability)
        updateHeight(binding.vIntelligence, powerStats.intelligence)
        updateHeight(binding.vPower, powerStats.power)
        updateHeight(binding.vSpeed, powerStats.speed)
        updateHeight(binding.vStrength, powerStats.strength)
        updateHeight(binding.vCombat, powerStats.combat)
    }

    private fun updateHeight(view: View, stat: String?) {
        val params = view.layoutParams
        params.height = if (stat == null || stat == "null") {
            0
        } else {
            pxToDp(stat.toFloat())
        }
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            px,
            resources.displayMetrics
        ).toInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/10231952155129002/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}