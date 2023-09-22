package com.maxalva.androidmaster.superhero

import com.google.gson.annotations.SerializedName

class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerStats: PowerStats,
    val image: Image
)

data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)