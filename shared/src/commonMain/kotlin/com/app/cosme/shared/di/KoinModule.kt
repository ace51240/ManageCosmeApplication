package com.app.cosme.shared.di

import AppDatabase
import com.app.cosme.shared.db.getRoomDatabase
import com.app.cosme.shared.repository.CosmeticRepository
import com.app.cosme.shared.repository.CosmeticRepositoryImpl
import com.app.cosme.shared.repository.RecipeRepository
import com.app.cosme.shared.repository.RecipeRepositoryImpl
import com.app.cosme.shared.usecase.RegisterCosmeticUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

expect val databaseBuilderModule: Module

val databaseModule = module {
    // シングルトンで宣言、get()でRoomDatabase.Builderインスタンスを取得し、データを挿入
    single { getRoomDatabase(builder = get()) }

    // シングルトンで宣言、get<AppDatabase>()でAppDatabaseオブジェクトを受け取り、DAOを挿入
    single { get<AppDatabase>().cosmeticDao() }
    single { get<AppDatabase>().recipeDao() }
    single { get<AppDatabase>().recipeCosmeticDao() }

    // シングルトンでインターフェースと実装クラスをバインドし挿入
    single<CosmeticRepository> { CosmeticRepositoryImpl(cosmeticDao = get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(recipeDao = get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(recipeDao = get()) }
}

val sharedModule = module {
    // factoryで毎度インスタンス生成し、repositoryを挿入、
    factory { RegisterCosmeticUseCase(cosmeticRepository = get()) }

}