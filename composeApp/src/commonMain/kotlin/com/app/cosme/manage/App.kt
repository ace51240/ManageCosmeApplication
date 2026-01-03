package com.app.cosme.manage

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.cosme.ui.CosmeticDetailScreen
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
            composable(
                route = "cosmetic_detail/{cosmeticId}",
                arguments = listOf(navArgument("cosmeticId") { type = NavType.IntType })
            ) { backStackEntry ->
                val cosmeticId = backStackEntry.arguments?.getInt("cosmeticId") ?: 0
                CosmeticDetailScreen(navController = navController, cosmeticId = cosmeticId)
            }
        }
    }
}