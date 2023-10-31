package com.example.project_1.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface ScreenInterface{

     val route : String
     val title: String
    val icon: ImageVector?

}

sealed class Screen {
    object Home : ScreenInterface {
        override val route = "homeScreen"
        override val title = "Home"
        override val icon = Icons.Outlined.Home
    }

    object Login : ScreenInterface {
        override val route = "loginScreen"
        override val title = "Login"
        override val icon = null
    }

    object SignUp : ScreenInterface {
        override val route = "signUpScreen"
        override val title = "SignUp"
        override val icon = null
    }

    object MatchingGame : ScreenInterface {
        override val route = "matchingGameScreen"
        override val title = "Matching Game"
        override val icon = null
        const val packageIdArg = "package_id"
        val routeWithArgs = "$route/{$packageIdArg}"
        val arguments = listOf(navArgument(packageIdArg) { type = NavType.IntType })
    }

    object UnscrambleGame : ScreenInterface {
        override val route = "unscramble"
        override val title = "Unscramble Sentence"
        override val icon = null
        const val packageIdArg = "package_id"
        val routeWithArgs = "$route/{$packageIdArg}"
        val arguments = listOf(navArgument(packageIdArg) { type = NavType.IntType })
    }

    object Rating : ScreenInterface {
        override val route = "ratingScreen"
        override val title = "Rating"
        override val icon = null
        const val packageIdArg = "package_id"
        val routeWithArgs = "$route/{$packageIdArg}"
        val arguments = listOf(navArgument(packageIdArg) { type = NavType.IntType })
    }
}


