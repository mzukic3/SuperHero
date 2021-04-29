package com.mzukic.superhero.data.network.response

import com.google.gson.annotations.SerializedName
import com.mzukic.superhero.data.model.SuperHero

data class SuperHeroResponse(
    @SerializedName("appearance")
    val appearance: Appearance? = null,
    @SerializedName("biography")
    val biography: Biography? = null,
    @SerializedName("connections")
    val connections: Connections? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("image")
    val image: Image? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("powerstats")
    val powerstats: Powerstats? = null,
    @SerializedName("work")
    val work: Work? = null
) {
    fun mapToDomain(): SuperHero {
        return SuperHero(
            id ?: "",
            name ?: "Unknown",
            biography?.fullName ?: "Unknown",
            image?.url ?: "",
            biography?.publisher ?: "Unknown",
            biography?.placeOfBirth ?: "Unknown",
            biography?.firstAppearance ?: "Unknown",
            biography?.alterEgos ?: "Unknown",
            biography?.alignment ?: "Unknown",
            biography?.aliases ?: listOf()
        )
    }
}
