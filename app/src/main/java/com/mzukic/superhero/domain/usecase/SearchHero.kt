package com.mzukic.superhero.domain.usecase

import com.mzukic.superhero.data.model.SuperHero
import com.mzukic.superhero.data.repository.SuperHeroesRepository
import com.mzukic.superhero.util.Either
import java.lang.Exception
import javax.inject.Inject

class SearchHero @Inject constructor(
    private val repository: SuperHeroesRepository
) {
    suspend operator fun invoke(heroName: String): Either<Exception, List<SuperHero>> =
        repository.searchHeroes(heroName)
}
