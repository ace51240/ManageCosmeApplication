package com.app.cosme.manage.di

import com.app.cosme.shared.di.databaseBuilderModule
import com.app.cosme.shared.di.databaseModule
import com.app.cosme.shared.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}, vararg additionalModules: Module) =
    startKoin {
        appDeclaration()
        modules(
            listOf(
                platformModule(),
                viewModelModule,
                databaseBuilderModule,
                databaseModule,
                sharedModule
            ) + additionalModules)
    }