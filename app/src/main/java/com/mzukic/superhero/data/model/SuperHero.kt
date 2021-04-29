package com.mzukic.superhero.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuperHero(
    val id: String,
    val name: String,
    val alternativeName: String,
    val imageUrl: String,
    val publisher: String,
    val placeOfBirth: String,
    val firstAppearance: String,
    val alterEgos: String,
    val alignment: String,
    val aliases: List<String>
) : Parcelable
