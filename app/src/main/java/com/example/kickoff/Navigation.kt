package com.example.kickoff

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kickoff.ui.FinalsScreen
import com.example.kickoff.ui.GroupsScreen
import com.example.kickoff.ui.MainScreen
import com.example.kickoff.ui.QuarterfinalsScreen
import com.example.kickoff.ui.SemiFinalsScreen
import com.example.kickoff.ui.UsernameScreen

const val MAIN_SCREEN = "main_screen"
const val USERNAME_SCREEN = "username_screen"
const val GROUPS_SCREEN = "groups_screen"
const val QUARTERFINALS_SCREEN = "quarterfinals_screen"
const val SEMIFINALS_SCREEN = "semifinals_screen"
const val FINALS_SCREEN = "finals_screen"

@Composable
fun AppNavigation()
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN,
        modifier = Modifier.fillMaxSize()
    )
    {
        composable(MAIN_SCREEN) {
            MainScreen(navController = navController)
        }

        composable(USERNAME_SCREEN) {
            UsernameScreen(navController = navController)
        }

        composable(GROUPS_SCREEN) {
            GroupsScreen(navController = navController)
        }

        composable(QUARTERFINALS_SCREEN) {
            QuarterfinalsScreen(navController = navController)
        }

        composable(SEMIFINALS_SCREEN) {
            SemiFinalsScreen(navController = navController)
        }

        composable(FINALS_SCREEN) {
            FinalsScreen(navController = navController)
        }
    }
}

