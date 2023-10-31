package com.example.project_1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.project_1.nav.BottomNavBar
import com.example.project_1.nav.MyNavHost
import com.example.project_1.repo.PackageRepo
import com.example.project_1.repo.UserRepo
import com.example.project_1.screens.Header

import com.example.project_1.ui.theme.Project_1Theme
import com.example.project_1.viewModels.MatchingGameViewModel
import com.example.project_1.viewModels.UnscrambleSentenceViewModel
import com.example.project_1.viewModels.UserViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TO TEST :

        val packageList = PackageRepo.getPackagesFromJson(this).toMutableStateList()
        Log.d("Ja", "onCreate: ${packageList.map { it.title }}")

        //Test --> Works
        val users = UserRepo.getUsersFromJson(this).toMutableStateList()
        Log.d("Al", "onCreate: ${users.map { it.firstName }}")


        setContent {
            val userViewModel : UserViewModel = viewModel()
            val matchingGameViewModel : MatchingGameViewModel = viewModel()
            val unscrambleSentenceViewModel : UnscrambleSentenceViewModel = viewModel()
            val navController = rememberNavController()
            Project_1Theme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,

                ) {
                    // nevagat
//                    var screenId by rememberSaveable {mutableStateOf(0)}
//
//                    when(screenId ){
//                       // 0 -> HomeScreen({screenId = 1}) {screenId = 1}
//                        1 -> LoginScreen{screenId = 2}
//                        2 ->  SingUpScreen()
//                    }



//                     HomeScreen()
                    // LoginScreen()
                   // SingUpScreen()
                   // MatchingWordDefinitonGameScreen()

                Scaffold(
                 topBar = {
                          Header(userViewModel, navController)
                 },
                bottomBar = {
                            BottomNavBar(navController = navController)
                }

                ) {
                    MyNavHost(navController, paddingValues = it,userViewModel, matchingGameViewModel, unscrambleSentenceViewModel)
//                    MatchingWordDefinitonGameScreen(viewModel = matchingGameViewModel, packageid = 2,
//                        modifier = Modifier.padding(it)
//                  )
                }

                }
            }
        }
    }
}


//Unit --> void nothing returned



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project_1Theme {
//        LingoSnacksButton("Get Started", onClickAction = {})
    }
}