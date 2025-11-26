package com.app.cosme.di

import com.app.cosme.manage.RegisterCosmeticViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { RegisterCosmeticViewModel(registerCosmeticUseCase = get()) }
}