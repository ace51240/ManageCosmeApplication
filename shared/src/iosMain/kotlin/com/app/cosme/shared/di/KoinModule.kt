package com.app.cosme.shared.di

import com.app.cosme.shared.db.getIOSDatabaseBuilder
import org.koin.dsl.module

actual val databaseBuilderModule = module {
    single { getIOSDatabaseBuilder() }
}