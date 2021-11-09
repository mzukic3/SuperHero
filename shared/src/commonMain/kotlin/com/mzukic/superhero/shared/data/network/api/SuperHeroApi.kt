package com.mzukic.superhero.shared.data.network.api

import com.mzukic.superhero.shared.data.network.response.SuperHeroSearchResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*


class Test()
class SuperHeroApi(val test: Test) {

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    suspend fun searchHeroes(name: String): SuperHeroSearchResponse {
        val test = client.get<SuperHeroSearchResponse> {
            url("$baseUrl/search/$name")
        }
        return  test
    }
    companion object {
        private const val baseUrl = "https://superheroapi.com/api/1311410919190147/"
    }
}