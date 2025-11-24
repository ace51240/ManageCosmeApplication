package com.app.cosme.shared.di

import com.app.cosme.shared.db.CosmeDatabase
import com.app.cosme.shared.repository.CosmeticRepository
import com.app.cosme.shared.repository.CosmeticRepositoryImpl
import com.app.cosme.shared.repository.RecipeRepository
import com.app.cosme.shared.repository.RecipeRepositoryImpl
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule, sharedModule)
    }

// for iOS
fun initKoin() = initKoin {}

val sharedModule = module {
    single { CosmeDatabase(driver = get()) }
    single<CosmeticRepository> { CosmeticRepositoryImpl(database = get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(database = get()) }
}

expect val platformModule: org.koin.core.module.Module