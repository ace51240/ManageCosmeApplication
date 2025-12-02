package com.app.cosme.manage

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.cosme.ui.HomeScreen
import com.app.cosme.ui.RegisterCosmeticScreen

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("register_cosmetic") {
                RegisterCosmeticScreen(navController = navController)
            }
        }
    }
}