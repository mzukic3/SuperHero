package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Biography(
    @SerialName("aliases")
    val aliases: List<String>,
    @SerialName("alignment")
    val alignment: String,
    @SerialName("alter-egos")
    val alterEgos: String,
    @SerialName("first-appearance")
    val firstAppearance: String,
    @SerialName("full-name")
    val fullName: String,
    @SerialName("place-of-birth")
    val placeOfBirth: String,
    @SerialName("publisher")
    val publisher: String
)