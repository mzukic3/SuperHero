package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperHeroSearchResponse(
    @SerialName("response")
    val response: String,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("results-for")
    val resultsFor: String
)