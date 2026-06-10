package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        MeshBackground()

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(
                    onNavigateToSignup = { navController.navigate("signup") },
                    onLoginSuccess = { 
                        navController.navigate("main") {
                            popUpTo("login") { inclusive = true }
                        } 
                    }
                )
            }
            composable("signup") {
                SignupScreen(
                    onNavigateToLogin = { 
                        navController.navigate("login") {
                            popUpTo("signup") { inclusive = true }
                        } 
                    },
                    onSignupSuccess = {
                        navController.navigate("main") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("main") {
                EcoSyncApp(onNavigateToBuyPower = { navController.navigate("buyPower") })
            }
            composable("buyPower") {
                BuyPowerScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
