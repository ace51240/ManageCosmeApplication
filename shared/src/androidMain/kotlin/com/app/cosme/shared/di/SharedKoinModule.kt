package com.app.cosme.shared.di

import com.app.cosme.shared.database.AndroidDatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single { AndroidDatabaseDriverFactory(context = get()).createDriver() }
}