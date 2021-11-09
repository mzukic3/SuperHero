package com.mzukic.superhero.shared.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("url")
    val url: String
)