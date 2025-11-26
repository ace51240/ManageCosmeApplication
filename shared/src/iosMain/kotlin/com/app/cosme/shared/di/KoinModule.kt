package com.app.cosme.shared.di

import com.app.cosme.shared.database.IOSDataBaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single { IOSDataBaseDriverFactory().createDriver() }
}