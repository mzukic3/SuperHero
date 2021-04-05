package com.mzukic.superhero.ui.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.data.repository.SuperHeroesRepository
import com.mzukic.superhero.util.Either
import com.mzukic.superhero.util.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SearchViewModel @ViewModelInject
constructor(
    private val superHeroesRepository: SuperHeroesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val superHeroesLiveData = MutableLiveData<Either<Exception, List<SuperHero>>>()
    private val loadingStateLiveData = MutableLiveData<LoadingState>()

    fun searchHeroes(heroName: String) {
        viewModelScope.launch {
            loadingStateLiveData.postValue(LoadingState.LOADING)
            val response = withContext(Dispatchers.IO) {
                superHeroesRepository.searchHeroes(heroName)
            }
            loadingStateLiveData.postValue(LoadingState.LOADED)
            superHeroesLiveData.postValue(response)
        }
    }

    fun observeSuperHeroesResults() = superHeroesLiveData
    fun observeLoadingState() = loadingStateLiveData
}
