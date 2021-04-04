package com.mzukic.superhero.data.network

import com.google.common.truth.Truth.assertThat
import com.mzukic.superhero.data.network.api.SuperHeroApiService
import com.mzukic.superhero.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class SuperHeroServiceTest: ApiAbstract<SuperHeroApiService>() {

    private lateinit var service: SuperHeroApiService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @Before
    fun initService() {
        service = createService(SuperHeroApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonInfoFromNetworkTest() = runBlocking {
        enqueueResponse("/SearchBatmanResponse.json")
        val response = service.searchSuperHeroes("batman")
        val responseBody = response.body()
        mockWebServer.takeRequest()

        assertThat(responseBody).isNotNull()
        assertThat(responseBody?.response).isEqualTo("success")
        assertThat(responseBody?.resultsFor).isEqualTo("batman")
        assertThat(responseBody?.superHeroesResponse?.size).isEqualTo(3)
        assertThat(responseBody?.superHeroesResponse?.first()?.id).isEqualTo("69")
        assertThat(responseBody?.superHeroesResponse?.first()?.name).isEqualTo("Batman")
    }
}