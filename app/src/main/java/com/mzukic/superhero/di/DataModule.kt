package com.mzukic.superhero.di

import com.mzukic.superhero.BuildConfig
import com.mzukic.superhero.data.network.api.SuperHeroApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideBaseURL() = "https://superheroapi.com/api/${BuildConfig.API_KEY}/"

    @Singleton
    @Provides
    fun provideHttpClient(apiKey: String): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideSuperHeroApiService(httpClient: OkHttpClient, baseUrl: String): SuperHeroApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .baseUrl(baseUrl)
            .build()
            .create(SuperHeroApiService::class.java)
    }
}
