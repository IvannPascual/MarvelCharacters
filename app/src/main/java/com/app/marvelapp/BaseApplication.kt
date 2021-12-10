package com.app.marvelapp

import android.app.Application
import com.app.marvelapp.common.commonsModule
import com.app.marvelapp.common.data.local.databaseModule
import com.app.marvelapp.common.data.remote.apiModule
import com.app.marvelapp.feature.detail.di.detailCharacterModule
import com.app.marvelapp.feature.listofcharacters.di.listOfCharactersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDi()
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    apiModule,
                    listOfCharactersModule,
                    detailCharacterModule,
                    commonsModule,
                    databaseModule
                )
            )
        }
    }


}
