package com.app.cosme.manage.di

import com.app.cosme.manage.RegisterCosmeticViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val viewModelModule = module {
    factory { RegisterCosmeticViewModel(registerCosmeticUseCase = get()) }
}