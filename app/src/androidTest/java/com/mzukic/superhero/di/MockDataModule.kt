package com.mzukic.superhero.di

import com.mzukic.superhero.data.network.api.SuperHeroApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
object MockDataModule {

    @Singleton
    @Provides
    fun provideBaseURL() = "http://localhost:8080/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
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
            .baseUrl(baseUrl)
            .client(httpClient)
            .build()
            .create(SuperHeroApiService::class.java)
    }
}
