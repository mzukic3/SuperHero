package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Connections(
    @SerialName("group-affiliation")
    val groupAffiliation: String,
    @SerialName("relatives")
    val relatives: String
)