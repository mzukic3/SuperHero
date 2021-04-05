package com.mzukic.superhero.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.data.repository.SuperHeroesRepository
import com.mzukic.superhero.exception.EndpointRequestFailedException
import com.mzukic.superhero.exception.NoConnectionException
import com.mzukic.superhero.util.Either
import com.mzukic.superhero.util.MainCoroutineRule
import com.mzukic.superhero.util.MockUtils
import com.mzukic.superhero.util.getOrAwaitValue
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    private val repository: SuperHeroesRepository = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun `Search heroes returns success`() = runBlocking {
        val mockResult = Either.Success(MockUtils.mockSuperHeroes())
        whenever(repository.searchHeroes(HERO_NAME)).thenReturn(
            mockResult
        )
        viewModel.searchHeroes(HERO_NAME)

        assertThat(viewModel.searchSuperHeroResult.getOrAwaitValue())
            .isEqualTo(SearchViewModel.SearchSuperHeroResult.Success(mockResult.data))

        verify(repository, atLeastOnce()).searchHeroes(HERO_NAME)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Search heroes no internet connection`() = runBlocking {
        val mockResult = Either.Exception(NoConnectionException())
        whenever(repository.searchHeroes(HERO_NAME)).thenReturn(
            mockResult
        )
        viewModel.searchHeroes(HERO_NAME)

        assertThat(viewModel.searchSuperHeroResult.getOrAwaitValue())
            .isEqualTo(SearchViewModel.SearchSuperHeroResult.NoConnectionError)

        verify(repository, atLeastOnce()).searchHeroes(HERO_NAME)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Search heroes no endpoint failed exception`() = runBlocking {
        val mockResult = Either.Exception(EndpointRequestFailedException())
        whenever(repository.searchHeroes(HERO_NAME)).thenReturn(
            mockResult
        )
        viewModel.searchHeroes(HERO_NAME)

        assertThat(viewModel.searchSuperHeroResult.getOrAwaitValue())
            .isEqualTo(SearchViewModel.SearchSuperHeroResult.EndpointFailedError)

        verify(repository, atLeastOnce()).searchHeroes(HERO_NAME)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `Search heroes no heroes found`() = runBlocking {
        val mockResult = Either.Success(listOf<SuperHero>())
        whenever(repository.searchHeroes(HERO_NAME)).thenReturn(
            mockResult
        )
        viewModel.searchHeroes(HERO_NAME)

        assertThat(viewModel.searchSuperHeroResult.getOrAwaitValue())
            .isEqualTo(SearchViewModel.SearchSuperHeroResult.NoHeroesFound)

        verify(repository, atLeastOnce()).searchHeroes(HERO_NAME)
        verifyNoMoreInteractions(repository)
    }

    companion object {
        const val HERO_NAME = "batman"
    }
}
