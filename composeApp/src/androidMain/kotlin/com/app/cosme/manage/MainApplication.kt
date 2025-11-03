package com.app.cosme.manage

import android.app.Application
import com.app.cosme.manage.viewmodel.AppViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(module)
        }
    }

    private val module: Module = module {
        viewModel { AppViewModel() }
    }
}
