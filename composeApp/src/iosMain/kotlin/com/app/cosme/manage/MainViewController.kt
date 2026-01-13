package com.app.cosme.manage

import androidx.compose.ui.window.ComposeUIViewController
import com.app.cosme.manage.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()

    return ComposeUIViewController { App() }
}