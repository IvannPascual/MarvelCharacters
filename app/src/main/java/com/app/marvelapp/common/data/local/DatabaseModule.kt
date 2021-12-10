package com.app.marvelapp.common.data.local

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): MarvelDatabase {
        return Room.databaseBuilder(application, MarvelDatabase::class.java, "Marvel")
            .fallbackToDestructiveMigration()
            .build()
    }
    fun provideCountriesDao(database: MarvelDatabase): FavoriteDao {
        return  database.favoriteDao
    }

    single { provideDatabase(androidApplication()) }
    factory { provideCountriesDao(get())}

}