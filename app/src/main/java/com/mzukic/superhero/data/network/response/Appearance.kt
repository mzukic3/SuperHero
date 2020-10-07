package com.mzukic.superhero.data.network.response

import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("eye-color")
    val eyeColor: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("hair-color")
    val hairColor: String? = null,
    @SerializedName("height")
    val height: List<String>? = null,
    @SerializedName("race")
    val race: String? = null,
    @SerializedName("weight")
    val weight: List<String>? = null
)
