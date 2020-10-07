package com.mzukic.superhero.data.network.response

import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("group-affiliation")
    val groupAffiliation: String? = null,
    @SerializedName("relatives")
    val relatives: String? = null
)
