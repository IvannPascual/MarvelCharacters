package com.app.marvelapp.common.data.remote

import com.app.marvelapp.common.data.remote.ApiProvider.provideMarvelApi
import com.app.marvelapp.common.data.remote.ApiProvider.provideLoggingInterceptor
import com.app.marvelapp.common.data.remote.ApiProvider.provideOkHttpClient
import com.app.marvelapp.common.data.remote.ApiProvider.provideRetrofit
import org.koin.dsl.module

val apiModule = module {
    factory { AuthInterceptor(get()) }
    factory { provideOkHttpClient(get(), get()) }
    factory { provideMarvelApi(get()) }
    factory { provideLoggingInterceptor() }
    factory { AuthHashGenerator() }
    single { provideRetrofit(get()) }
}
