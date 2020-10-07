package com.mzukic.superhero.data.network.api

import com.mzukic.superhero.data.network.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {

    @GET("search/{name}")
    suspend fun searchSuperHeroes(@Path(value = "name", encoded = true) name: String): Response<SearchResponse>
}
