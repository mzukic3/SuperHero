package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Work(
    @SerialName("base")
    val base: String,
    @SerialName("occupation")
    val occupation: String
)