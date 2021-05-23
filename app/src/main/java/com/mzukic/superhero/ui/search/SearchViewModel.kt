package com.mzukic.superhero.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.data.repository.SuperHeroesRepository
import com.mzukic.superhero.exception.NoConnectionException
import com.mzukic.superhero.util.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel
constructor(
    private val superHeroesRepository: SuperHeroesRepository,
) : ViewModel() {

    private val _searchSuperHeroResult = MutableLiveData<SearchSuperHeroResult>()
    val searchSuperHeroResult: LiveData<SearchSuperHeroResult> = _searchSuperHeroResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchHeroes(heroName: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = superHeroesRepository.searchHeroes(heroName)
            _isLoading.value = false
            when (result) {
                is Either.Success -> {
                    result.data.let { heroes ->
                        if (heroes.isEmpty()) {
                            _searchSuperHeroResult.value = SearchSuperHeroResult.NoHeroesFound
                        } else {
                            _searchSuperHeroResult.value = SearchSuperHeroResult.Success(heroes)
                        }
                    }
                }
                is Either.Exception -> {
                    _searchSuperHeroResult.value = when (result.exception) {
                        is NoConnectionException -> SearchSuperHeroResult.NoConnectionError
                        else -> SearchSuperHeroResult.EndpointFailedError
                    }
                }
            }
        }
    }

    sealed class SearchSuperHeroResult {
        data class Success(val heroes: List<SuperHero>) : SearchSuperHeroResult()
        object NoHeroesFound : SearchSuperHeroResult()
        object NoConnectionError : SearchSuperHeroResult()
        object EndpointFailedError : SearchSuperHeroResult()
    }
}
