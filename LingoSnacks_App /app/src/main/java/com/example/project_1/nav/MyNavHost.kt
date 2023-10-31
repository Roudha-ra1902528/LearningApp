package com.example.project_1.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_1.repo.PackageRepo
import com.example.project_1.screens.HomeScreen
import com.example.project_1.screens.LoginScreen
import com.example.project_1.screens.MatchingWordDefinitonGameScreen
import com.example.project_1.screens.RatingScreen
import com.example.project_1.screens.SingUpScreen
import com.example.project_1.screens.UnscrambleSentenceScreen
import com.example.project_1.viewModels.MatchingGameViewModel
import com.example.project_1.viewModels.UnscrambleSentenceViewModel
import com.example.project_1.viewModels.UserViewModel

@Composable
fun MyNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userViewModel: UserViewModel,
    matchingGameViewModel: MatchingGameViewModel,
    unscrambleSentenceViewModel: UnscrambleSentenceViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {
//        mapping of the route to the screen composables
        composable(route = Screen.Home.route) {
//            if (userViewModel.isLogedIn()) {
            val list = PackageRepo.learningPackages
            HomeScreen(matchingGameViewModel,
                unscrambleSentenceViewModel,
                userViewModel,
                list = list,
                modifier = Modifier.padding(paddingValues),
                onMatchGame = {
                    navController.navigateSingleTopTo("${Screen.MatchingGame.route}/${it}")
                },
                onUnscrambleGame = {
                    navController.navigateSingleTopTo("${Screen.UnscrambleGame.route}/${it}")
                },
                onRatePackage = {
                    navController.navigateSingleTopTo("${Screen.Rating.route}/${it}")
                },
                onLogIn = { navController.navigateSingleTopTo(Screen.Home.route); println("here")},
                onSignUp = { navController.navigateSingleTopTo(Screen.SignUp.route) }
            )
//            }else {
//                navController.navigateSingleTopTo(Screen.Login.route)
//            }
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                userViewModel,
                onSignUpClick = { navController.navigateSingleTopTo(Screen.SignUp.route) },
                onLogin = { navController.navigateSingleTopTo(Screen.Home.route) },
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable(
            route = Screen.SignUp.route
        ) {
            SingUpScreen(
                onSubmit = { navController.navigateSingleTopTo(Screen.Home.route) },
                userViewModel,
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable(
            route = Screen.MatchingGame.routeWithArgs,
            arguments = Screen.MatchingGame.arguments
        ) {
            val packageId = it.arguments?.getInt(Screen.MatchingGame.packageIdArg)!!
            matchingGameViewModel.setPackageId(packageId!!)
            matchingGameViewModel.resetGame()
            MatchingWordDefinitonGameScreen(
                matchingGameViewModel, packageId, modifier = Modifier.padding(paddingValues)
            )
        }
        composable(
            route = Screen.Rating.routeWithArgs,
            arguments = Screen.Rating.arguments
        ) {
            val packageId = it.arguments?.getInt(Screen.Rating.packageIdArg)!!

            RatingScreen(userViewModel.currentUser, packageId)
        }
        composable(
            route = Screen.UnscrambleGame.routeWithArgs,
            arguments = Screen.UnscrambleGame.arguments
        ) {
            val packageId = it.arguments?.getInt(Screen.UnscrambleGame.packageIdArg)!!
//            unscrambleSentenceViewModel.setPackageId(package_id)
            UnscrambleSentenceScreen(
                unscrambleSentenceViewModel, packageId, modifier = Modifier.padding(paddingValues)
            ) { navController.navigateSingleTopTo(Screen.Home.route) }
        }


    }
}