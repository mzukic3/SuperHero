package com.mzukic.superhero.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("response")
    val response: String? = null,
    @SerializedName("results")
    val superHeroesResponse: List<SuperHeroResponse>? = null,
    @SerializedName("results-for")
    val resultsFor: String? = null
)
