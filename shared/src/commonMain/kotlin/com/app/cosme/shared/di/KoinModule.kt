package com.app.cosme.shared.di

import com.app.cosme.shared.db.CosmeDatabase
import com.app.cosme.shared.repository.CosmeticRepository
import com.app.cosme.shared.repository.CosmeticRepositoryImpl
import com.app.cosme.shared.repository.RecipeRepository
import com.app.cosme.shared.repository.RecipeRepositoryImpl
import com.app.cosme.shared.usecase.RegisterCosmeticUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}, vararg additionalModules: Module) =
    startKoin {
        appDeclaration()
        modules(listOf(platformModule, sharedModule) + additionalModules)
    }

val sharedModule = module {
    single { CosmeDatabase(driver = get()) }
    single<CosmeticRepository> { CosmeticRepositoryImpl(database = get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(database = get()) }

    // UseCase
    factory { RegisterCosmeticUseCase(cosmeticRepository = get()) }
}

expect val platformModule: Module