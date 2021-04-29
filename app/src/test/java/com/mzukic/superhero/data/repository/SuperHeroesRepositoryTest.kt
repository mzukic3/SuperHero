package com.mzukic.superhero.data.repository

import com.google.common.truth.Truth.assertThat
import com.mzukic.superhero.data.network.api.SuperHeroApiService
import com.mzukic.superhero.exception.EndpointRequestFailedException
import com.mzukic.superhero.util.Either
import com.mzukic.superhero.util.MainCoroutineRule
import com.mzukic.superhero.util.MockUtils
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SuperHeroesRepositoryTest {

    private lateinit var repository: SuperHeroesRepository
    private val apiService: SuperHeroApiService = mock()

    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = SuperHeroesRepository(apiService)
    }

    @Test
    fun `Test search super heroes returns success`(): Unit = runBlocking {
        val mockHeroResponse = MockUtils.mockSearchResponse()
        whenever(apiService.searchSuperHeroes(HERO_NAME)).thenReturn(
            Response.success(
                mockHeroResponse
            )
        )

        repository.searchHeroes(HERO_NAME).let {
            assertThat(it.isSuccess).isTrue()
            val result = (it as Either.Success).data
            assertThat(result.size).isEqualTo(mockHeroResponse.superHeroesResponse?.size)
            result.first().let { superHero ->
                assertThat(superHero.id).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.id)
                assertThat(superHero.name).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.name)
                assertThat(superHero.imageUrl).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.image?.url)
                assertThat(superHero.alignment).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.biography?.alignment)
                assertThat(superHero.aliases).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.biography?.aliases)
                assertThat(superHero.alterEgos).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.biography?.alterEgos)
                assertThat(superHero.alternativeName).isEqualTo(mockHeroResponse.superHeroesResponse?.first()?.biography?.fullName)
            }
        }
        verify(apiService, atLeastOnce()).searchSuperHeroes(name = HERO_NAME)
        verifyNoMoreInteractions(apiService)
    }

    @Test
    fun `Test search super heroes returns error`(): Unit = runBlocking {
        val mockHeroResponse = MockUtils.mockSearchResponse()
        whenever(apiService.searchSuperHeroes(HERO_NAME)).thenReturn(
            Response.error(400,"".toResponseBody())
        )

        repository.searchHeroes(HERO_NAME).let {
            assertThat(it.isException).isTrue()
            val exception = (it as Either.Exception).exception
            assertThat(exception).isInstanceOf(EndpointRequestFailedException::class.java)
        }
        verify(apiService, atLeastOnce()).searchSuperHeroes(name = HERO_NAME)
        verifyNoMoreInteractions(apiService)
    }

    companion object {
        const val HERO_NAME = "batman"
    }
}
