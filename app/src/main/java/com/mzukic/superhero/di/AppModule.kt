package com.mzukic.superhero.di

import com.mzukic.superhero.data.repository.SuperHeroesRepository
import com.mzukic.superhero.domain.usecase.SearchHero
import com.mzukic.superhero.shared.data.network.api.SuperHeroApi
import com.mzukic.superhero.shared.data.network.api.Test
import com.mzukic.superhero.ui.search.SearchViewModel
import org.koin.dsl.module

val appModule = module {

    single<SuperHeroApi> {
        SuperHeroApi(Test())
    }
    // single instance of HelloRepository
    single<SuperHeroesRepository> { SuperHeroesRepository(get()) }

    single { SearchViewModel(get()) }
}

val useCasesModule = module {
    factory {
        SearchHero(get())
    }
}