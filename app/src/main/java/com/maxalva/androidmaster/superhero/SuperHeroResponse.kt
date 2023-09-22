package com.maxalva.androidmaster.superhero

import com.google.gson.annotations.SerializedName

annotation class Json(val name: String)

data class SuperHeroResponse(
    @SerializedName("response") val status: String,
    @SerializedName("results") val superHeroes: List<SuperHero>
)

data class SuperHero(
    val id: String,
    val name: String,
//    val powerstats: Powerstats,
//    val biography: Biography,
//    val appearance: Appearance,
//    val work: Work,
//    val connections: Connections,
//    val image: Image
)

data class Appearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,

    @Json(name = "eye-color")
    val eyeColor: String,

    @Json(name = "hair-color")
    val hairColor: String
)

data class Biography(
    @Json(name = "full-name")
    val fullName: String,

    @Json(name = "alter-egos")
    val alterEgos: String,

    val aliases: List<String>,

    @Json(name = "place-of-birth")
    val placeOfBirth: String,

    @Json(name = "first-appearance")
    val firstAppearance: String,

    val publisher: String,
    val alignment: String
)

data class Connections(
    @Json(name = "group-affiliation")
    val groupAffiliation: String,

    val relatives: String
)

data class Image(
    val url: String
)

data class Powerstats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)

data class Work(
    val occupation: String,
    val base: String
)