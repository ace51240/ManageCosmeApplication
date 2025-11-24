package com.app.cosme.manage

import android.app.Application
import com.app.cosme.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CosmeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CosmeApplication)
            androidLogger()
        }
    }
}