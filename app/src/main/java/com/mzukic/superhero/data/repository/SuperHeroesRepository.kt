package com.mzukic.superhero.data.repository

import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.exception.EndpointRequestFailedException
import com.mzukic.superhero.exception.NoConnectionException
import com.mzukic.superhero.shared.data.network.api.SuperHeroApi
import com.mzukic.superhero.util.Either
import retrofit2.HttpException
import java.io.IOException

class SuperHeroesRepository constructor(private val apiService: SuperHeroApi) {

    suspend fun searchHeroes(heroName: String): Either<Exception, List<SuperHero>> {
        return try {
            val response = apiService.searchHeroes(heroName)

            Either.Success(response.results.map {
                SuperHero(
                    it.id,
                    it.name,
                    it.biography.fullName,
                    it.image.url,
                    it.biography.publisher,
                    it.biography.placeOfBirth,
                    "",
                    "",
                    "",
                    it.biography.aliases
                )
            })
        } catch (e: Exception) {
            return when (e) {
                is IOException -> Either.Exception(NoConnectionException())
                is HttpException -> Either.Exception(EndpointRequestFailedException())
                else -> {
                    Either.Exception(e)
                }
            }
        }
    }
}
