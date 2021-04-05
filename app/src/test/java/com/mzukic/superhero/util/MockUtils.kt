package com.mzukic.superhero.util

import com.mzukic.superhero.data.network.response.Biography
import com.mzukic.superhero.data.network.response.Image
import com.mzukic.superhero.data.network.response.SearchResponse
import com.mzukic.superhero.data.network.response.SuperHeroResponse

object MockUtils {
    fun mockSearchResponse() = SearchResponse(
        response = "success",
        resultsFor = "batman",
        superHeroesResponse = listOf(
            SuperHeroResponse(
                id = "1",
                name = "Batman",
                biography = Biography(
                    aliases = listOf(
                        "Batman II",
                        "The Tomorrow Knight"
                    ),
                    fullName = "Terry McGinnis",
                    alterEgos = "No alter egos found.",
                    placeOfBirth = "Gotham City, 25th Century",
                    firstAppearance = "Batman Beyond #1",
                    publisher = "DC Comics",
                    alignment = "good"
                ),
                image = Image("https://www.superherodb.com/pictures2/portraits/10/100/10441.jpg")
            )
        )
    )
}
