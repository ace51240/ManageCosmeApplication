package com.app.cosme.manage

import androidx.compose.ui.window.ComposeUIViewController
import com.app.cosme.di.viewModelModule
import com.app.cosme.shared.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin({}, viewModelModule)
    return ComposeUIViewController { App() }
}