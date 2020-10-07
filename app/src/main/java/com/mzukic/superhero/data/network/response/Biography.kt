package com.mzukic.superhero.data.network.response

import com.google.gson.annotations.SerializedName

data class Biography(
    @SerializedName("aliases")
    val aliases: List<String>? = null,
    @SerializedName("alignment")
    val alignment: String? = null,
    @SerializedName("alter-egos")
    val alterEgos: String? = null,
    @SerializedName("first-appearance")
    val firstAppearance: String? = null,
    @SerializedName("full-name")
    val fullName: String? = null,
    @SerializedName("place-of-birth")
    val placeOfBirth: String? = null,
    @SerializedName("publisher")
    val publisher: String? = null
)
