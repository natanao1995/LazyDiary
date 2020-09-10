package com.natanao.lazydiary.general

import android.app.Application
import android.graphics.Color
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.jakewharton.threetenabp.AndroidThreeTen
import com.natanao.lazydiary.R
import com.natanao.lazydiary.di.simpleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class LazyDiaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initThreeTen()
        initKoin()
        initEmojiCompat()
    }

    private fun initThreeTen() {
        AndroidThreeTen.init(this)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@LazyDiaryApplication)
            modules(simpleModule)
        }
    }

    private fun initEmojiCompat() { //todo may lead to crashes for the first launch without network on old devices
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
            .setReplaceAll(true)
            //.setEmojiSpanIndicatorEnabled(BuildConfig.DEBUG)
            .setEmojiSpanIndicatorColor(Color.GREEN)

        EmojiCompat.init(config)
    }
}