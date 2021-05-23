package com.mzukic.superhero.di

import com.mzukic.superhero.BuildConfig
import com.mzukic.superhero.data.network.api.SuperHeroApiService
import com.mzukic.superhero.data.repository.SuperHeroesRepository
import com.mzukic.superhero.ui.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // single instance of HelloRepository
    single<SuperHeroesRepository> { SuperHeroesRepository(get()) }

    single<String> { "https://superheroapi.com/api/${BuildConfig.API_KEY}/" }

    single<OkHttpClient> {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        httpClient.build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(get<String>())
            .build()
            .create(SuperHeroApiService::class.java)
    }
    single { SearchViewModel(get()) }
}