package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("appearance")
    val appearance: Appearance,
    @SerialName("biography")
    val biography: Biography,
    @SerialName("connections")
    val connections: Connections,
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val image: Image,
    @SerialName("name")
    val name: String,
    @SerialName("powerstats")
    val powerstats: Powerstats,
    @SerialName("work")
    val work: Work
)