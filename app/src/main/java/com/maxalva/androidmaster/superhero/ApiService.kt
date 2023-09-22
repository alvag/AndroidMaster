package com.maxalva.androidmaster.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search/{name}")
    suspend fun getSuperHeroes(@Path("name") name: String): Response<SuperHeroResponse>

}