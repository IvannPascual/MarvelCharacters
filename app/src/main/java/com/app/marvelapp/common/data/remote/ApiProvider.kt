package com.app.marvelapp.common.data.remote


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

private val json = Json {
    ignoreUnknownKeys = true
}

object ApiProvider  {

    private const val marvelBaseUrl = "https://gateway.marvel.com"

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(marvelBaseUrl).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
           .build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).build()
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        logger.level = HttpLoggingInterceptor.Level.BODY
        logger.level = HttpLoggingInterceptor.Level.HEADERS
        return logger
    }

    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)
}