package com.natanao.lazydiary.general

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.natanao.lazydiary.di.simpleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LazyDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@LazyDiaryApplication)
            modules(simpleModule)
        }
    }
}