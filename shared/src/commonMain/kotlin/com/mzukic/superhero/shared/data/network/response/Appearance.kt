package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Appearance(
    @SerialName("eye-color")
    val eyeColor: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("hair-color")
    val hairColor: String,
    @SerialName("height")
    val height: List<String>,
    @SerialName("race")
    val race: String,
    @SerialName("weight")
    val weight: List<String>
)