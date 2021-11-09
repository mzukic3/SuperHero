package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Powerstats(
    @SerialName("combat")
    val combat: String,
    @SerialName("durability")
    val durability: String,
    @SerialName("intelligence")
    val intelligence: String,
    @SerialName("power")
    val power: String,
    @SerialName("speed")
    val speed: String,
    @SerialName("strength")
    val strength: String
)