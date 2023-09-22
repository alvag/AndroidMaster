package com.maxalva.androidmaster.superhero

import com.google.gson.annotations.SerializedName

annotation class Json(val name: String)

data class SuperHeroListResponse(
    @SerializedName("response") val status: String,
    @SerializedName("results") val superHeroes: List<SuperHero>
)

data class SuperHero(
    val id: String,
    val name: String,
    val image: Image
)

data class Image(
    val url: String
)
