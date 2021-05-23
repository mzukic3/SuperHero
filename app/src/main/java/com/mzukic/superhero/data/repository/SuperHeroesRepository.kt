package com.mzukic.superhero.data.repository

import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.data.network.api.SuperHeroApiService
import com.mzukic.superhero.exception.EndpointRequestFailedException
import com.mzukic.superhero.exception.NoConnectionException
import com.mzukic.superhero.util.Either
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

class SuperHeroesRepository constructor(private val apiService: SuperHeroApiService) {

    suspend fun searchHeroes(heroName: String): Either<Exception, List<SuperHero>> {
        try {
            val response = apiService.searchSuperHeroes(heroName)
            return if (response.isSuccessful) {
                val superHeroes = response.body()?.superHeroesResponse?.map { it.mapToDomain() } ?: mutableListOf()
                Either.Success(superHeroes)
            } else {
                Either.Exception(EndpointRequestFailedException())
            }
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
