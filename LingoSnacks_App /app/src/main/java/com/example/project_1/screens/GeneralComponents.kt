package com.example.project_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.project_1.R
import com.example.project_1.nav.Screen
import com.example.project_1.nav.getCurrentRoute
import com.example.project_1.nav.navigateSingleTopTo
import com.example.project_1.viewModels.UserViewModel


@Composable
fun WelcomeMessage(userViewModel: UserViewModel) {
        println("${userViewModel.firstName.value} ${userViewModel.currentUser.value} ")
        Text(
            text = "Welcome ${userViewModel.currentUser.value?.firstName} ${userViewModel.currentUser.value?.lastName}!",
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 16.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp) // Make it bold and set the font size
        )
}
// shared on all the screen
@Composable
fun Header(userViewModel: UserViewModel, navController: NavHostController) {


    Column(modifier = Modifier.fillMaxWidth()
//        .background(
//            color = colorResource(
//                id = R.color.BackgroundFill
//            ))
    ) {


        Row(modifier = Modifier
            .fillMaxWidth()
            .drawBehind {

                val strokeWidth = 2 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.Black,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .padding(4.dp)

            , horizontalArrangement = if (userViewModel.isLogedIn()||getCurrentRoute(navController) != Screen.Login.route) Arrangement.SpaceBetween else Arrangement.End) {

            if (userViewModel.isLogedIn()) {
            WelcomeMessage(userViewModel)
            }else if(getCurrentRoute(navController) != Screen.Login.route){
                IconButton(
                    onClick = { navController.navigateSingleTopTo(Screen.Login.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "",
                        modifier = Modifier.size(40.dp).padding(start = 12.dp, top = 12.dp) // Adjust the size as needed
                    )
                }
            }


            Image(
                painter = painterResource(
                    id = R.drawable.logo),
                contentScale = ContentScale.Inside,
                contentDescription = null,
                modifier = Modifier.height(65.dp)
                )

        }

    }


}



