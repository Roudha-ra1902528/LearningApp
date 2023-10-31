package com.example.project_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_1.R
import com.example.project_1.components.RatingDialog
import com.example.project_1.model.LearningPackage
import com.example.project_1.repo.PackageRepo
import com.example.project_1.repo.RatingRepo
import com.example.project_1.viewModels.MatchingGameViewModel
import com.example.project_1.viewModels.UnscrambleSentenceViewModel
import com.example.project_1.viewModels.UserViewModel


@Composable
fun HomeScreen(
    matchViewModel: MatchingGameViewModel,
    unscrambleViewModel: UnscrambleSentenceViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    list: List<LearningPackage>,
    onMatchGame: (Int) -> Unit,
    onUnscrambleGame: (Int) -> Unit,
    onRatePackage: (Int) -> Unit,
    onLogIn: () -> Unit,
    onSignUp: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)

    ) {
        PakegeSide(
            matchViewModel,
            unscrambleViewModel,
            userViewModel,
            list,
            onMatchGame = { onMatchGame(it) },
            onUnscrambleGame = { onUnscrambleGame(it) },
            onRatePackage = { onRatePackage(it) },
            onLogIn,
            onSignUp
            )

    }

}

@Composable
fun PakegeSide(
    matchViewModel: MatchingGameViewModel, unscrambleViewModel: UnscrambleSentenceViewModel,
    userViewModel: UserViewModel, list: List<LearningPackage>, onMatchGame: (Int) -> Unit, onUnscrambleGame: (Int) -> Unit,
    onRatePackage: (Int) -> Unit, onLogIn: () -> Unit, onSignUp: () -> Unit
) {
    Box() {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.heightIn(16.dp)
        ) {
            items(list) { item ->
                PackageGamesElements(
                    matchViewModel,
                    unscrambleViewModel,
                    userViewModel,
                    item,
                    onMatchGame = { onMatchGame(it) },
                    onUnscrambleGame = { onUnscrambleGame(it) },
                    onRatePackage = { onRatePackage(it) },
                    onLogIn,
                    onSignUp
                )

            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PackageGamesElements(
    matchViewModel: MatchingGameViewModel, unscrambleViewModel: UnscrambleSentenceViewModel, userViewModel: UserViewModel, learningPackage: LearningPackage, onMatchGame: (Int) -> Unit, onUnscrambleGame: (Int) -> Unit,
    onRatePackage: (Int) -> Unit, onLogIn: () -> Unit, onSignUp: () -> Unit
) {
    var openAlertDialog by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row() {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start){
                    Image(
                        painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(190.dp)
                            .padding(start = 8.dp)
                    )

                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color(0xFFFFD600),
                            modifier = Modifier.padding(bottom = 2.dp).size(50.dp)
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 0.dp),
                            text = "${RatingRepo.averageRating(learningPackage.packageId)}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = { openAlertDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            modifier = Modifier.wrapContentWidth(),
                            contentPadding = PaddingValues(0.dp) // Set content padding to zero
                        ) {
                            Text(
                                "Rate",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline,
                                    letterSpacing = 1.sp
                                )
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Author : ", modifier = Modifier)
                    Text(text = "Category : ", modifier = Modifier)
                    Text(text = "Description : ", modifier = Modifier)
                    Text(text = "Language : ", modifier = Modifier)
                    Text(text = "Level : ", modifier = Modifier)
                    Text(text = "Title : ", modifier = Modifier)
                    Text(text = "Version : ", modifier = Modifier)
                }

            }



            Spacer(modifier = Modifier.heightIn(25.dp))

            FlowRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD5D4D1))
                    .padding(15.dp)
            ) {

                Button(
                    contentPadding = PaddingValues(0.dp),
                    onClick = { onMatchGame(learningPackage.packageId) },
                    modifier = Modifier
                        .width(105.dp)
                        .height(100.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(5.dp))
                ) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Matching",
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Words",
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${matchViewModel.correctAnswer.value}/${matchViewModel.trialsLength}",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Button(
                    contentPadding = PaddingValues(0.dp),
                    onClick = { onUnscrambleGame(learningPackage.packageId) },
                    modifier = Modifier
                        .width(105.dp)
                        .height(100.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(5.dp))
                ) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Unscramble",
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Sentence",
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${unscrambleViewModel.scores[learningPackage.packageId]}/${PackageRepo.getSentences(learningPackage.packageId).size}",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Button(
                    contentPadding = PaddingValues(0.dp),
                    onClick = {  },
                    modifier = Modifier
                        .width(105.dp)
                        .height(100.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(5.dp))
                ) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Flash",
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Cards",
                            textAlign = TextAlign.Center
                        )
                    }
                }


                when {
                    openAlertDialog -> {
                        RatingDialog(
                            onSignUp,
                            userViewModel,
                            onLogIn,
                            userViewModel.email.value,
                            learningPackage.packageId,
                            onDismissRequest = { openAlertDialog = false },
                            onConfirmation = {
                                openAlertDialog = false
                                println("Confirmation registered") // Add logic here to handle confirmation.
                            },
                            dialogTitle = "Rate: ${learningPackage.title}",
                            dialogText = "${learningPackage.description}",
                            icon = Icons.Default.Info
                        ) { rating, comment, date ->
                            println("$rating $comment $date")
                            openAlertDialog = false
                        }
                    }
                }


            }

        }
    }


}

//                    Button(onClick = { onRatePackage(learningPackage.packageId) }) {
//                        Text(text = "Rate")
//                    }

//Just to check
private val PackegeDefItems = listOf(
    R.drawable.xby4fbiv,
    R.drawable.logo,
    R.drawable.logo,
    R.drawable.logo,
    R.drawable.logo,
    R.drawable.logo,
    R.drawable.logo,
    R.drawable.logo,
    R.drawable.logo
)


//package com.example.project_1.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ExperimentalLayoutApi
//import androidx.compose.foundation.layout.FlowRow
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.project_1.R
//import com.example.project_1.components.RatingDialog
//import com.example.project_1.model.LearningPackage
//import com.example.project_1.viewModels.UserViewModel
//
//
//@Composable
//fun HomeScreen(modifier: Modifier = Modifier, list: List<LearningPackage>
//               , onMatchGame: (Int) -> Unit, onUnscrambleGame: (Int) -> Unit,
//               onRatePackage: (Int) -> Unit ) {
//
//    Column(
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        //verticalArrangement = Arrangement.spacedBy(25.dp)
//        verticalArrangement = Arrangement.spacedBy(50.dp)
//
//
//    ) {
//
//
////            Header()
//        PakegeSide(list, onMatchGame = { onMatchGame(it) }, onUnscrambleGame = { onUnscrambleGame(it) },
//            onRatePackage = { onRatePackage(it) })
////        GamesRollScroll()
////        LingoSnacksButton(
////            stringResource(R.string.get_started),
////            onClickAction = onGetStartedClicked) // even throws from the main
////
////        Text(
////            text = stringResource(R.string.homescreen_motivation_msg),
////            modifier = Modifier.padding(start = 8.dp)
////
////        )
////
////        Text(
////            text = stringResource(R.string.Rate),
////            color = colorResource(id = R.color.SignUp),
//////            modifier = Modifier.clickable { onRateClicked() }
////        )
//
//
//    }
//
//}
//
////
////
////@Composable
////fun LingoSnacksButton(name: String, modifier: Modifier = Modifier, onClickAction: () -> Unit) {
////
////    //hight , width
////    OutlinedButton(onClick = onClickAction, modifier = modifier.padding(0.5.dp).width(255.dp)
////        .height(61.dp),
////        shape  = RoundedCornerShape(corner = CornerSize(0.dp))
////        ) {
////        Text(
////            text = "$name",  color = colorResource(id = R.color.PageTitlesColor),
////            fontWeight = FontWeight.ExtraBold
////
////
////            // Bold , color
////        )
////    }
////
////}
////
////@Composable
////fun GamesRollScroll() {
////
////    Button(
////        onClick = { /*TODO*/ }, modifier = Modifier
////            .background(Color.Gray)
////            .fillMaxWidth()
////            .heightIn(155.dp)
////    ) {
////
////    }
////
////}
//
//
//@Composable
//fun PakegeSide(list: List<LearningPackage>,onMatchGame: (Int) -> Unit, onUnscrambleGame: (Int) -> Unit,
//               onRatePackage: (Int) -> Unit) {
//
//    Column {
//
//
//        Row {
//            // search Bar
//        }
//
//        Box(){
//
//            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally
//                ,modifier = Modifier.heightIn(16.dp)
////                    , contentPadding = PaddingValues(horizontal = 16.dp)
//            ) {
//
////                    val dataList = listOf("Abhishek", "Harshit", "Gaurav")
////                    items(dataList) {it
////                    }
//                items( list ){
//                        item  ->    PackageGamesElements (item, onMatchGame = { onMatchGame(it) },
//                    onUnscrambleGame = { onUnscrambleGame(it) }, onRatePackage = { onRatePackage(it) })
//                }
//
//            }
//
//        }
//
//    }
//
//
//
//
//}
//
//
//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun PackageGamesElements(learningPackage: LearningPackage,onMatchGame:(Int)-> Unit, onUnscrambleGame: (Int) -> Unit,
//                         onRatePackage: (Int) -> Unit) {
//
//    var openAlertDialog by remember { mutableStateOf(false) }
//
//    Column() {
//
//
//
//        Card (modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)){
//            Row (){
//
//
//                Image(painterResource(id = R.drawable.logo), contentDescription = null
//                    , modifier = Modifier
//                        .size(190.dp))
////                        .padding(16.dp, 0.dp))
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(8.dp),
//                    modifier = Modifier.padding(16.dp)
//                ) {
////
//
//                    Text(text = "Author : ${learningPackage.author} " , modifier = Modifier)
//                    Text(text = "Category : " , modifier = Modifier)
//                    Text(text = "Description : " , modifier = Modifier)
//                    Text(text = "Language : " , modifier = Modifier)
//                    Text(text = "Level : " , modifier = Modifier)
//                    Text(text = "Title : " , modifier = Modifier)
//                    Text(text = "Version : " , modifier = Modifier)
//
//                }
//            }
//            Spacer(modifier = Modifier.heightIn(25.dp))
//            FlowRow (modifier = Modifier
//                .background(Color.Yellow)
//                .padding(32.dp)){
//
////                    Image(painterResource(id = R.drawable.logo), contentDescription = null )
//                Button(onClick = { onMatchGame(learningPackage.packageId) }) {
//                    Text(text = "Matching Words")
//
//                }
//
//                Button(onClick = { onUnscrambleGame(learningPackage.packageId) }) {
//                    Text(text = "Unscramble Words")
//                }
//
////                    Button(onClick = { onRatePackage(learningPackage.packageId) }) {
////                        Text(text = "Rate")
////                    }
//
//                Button(
//                    onClick = { openAlertDialog = true },
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text("Rate")
//                }
//
//                when {
//                    // ...
//                    openAlertDialog -> {
//                        RatingDialog(
//                            onDismissRequest = { openAlertDialog = false },
//                            onConfirmation = {
//                                openAlertDialog = false
//                                println("Confirmation registered") // Add logic here to handle confirmation.
//                            },
//                            dialogTitle = "Rate: Travel & Food",
//                            dialogText = "This is an example of an alert dialog with buttons.",
//                            icon = Icons.Default.Info
//                        ){
//                                rating, comment, date -> println("$rating $comment $date")
//                            openAlertDialog = false
//                        }
//                    }
//                }
//
//
//
//            }
//
//
////        Text(text = stringResource(text),
////            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
////            style = MaterialTheme.typography.bodyMedium
////        )
//        }
//    }
//
//
//}
//
////Just to check
//private val PackegeDefItems = listOf(
//    R.drawable.xby4fbiv,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo
//)