package com.example.project_1.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_1.R
import com.example.project_1.viewModels.UserViewModel


@Composable
fun LoginScreen(userViewModel: UserViewModel, onSignUpClick : () -> Unit, onLogin:() -> Unit,modifier: Modifier = Modifier) {

    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = modifier

    ) {

//        Header()
        LoginForm(userViewModel, onSignUpClick, onLogin)




//        Column(
//            modifier = Modifier.heightIn(3.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(25.dp),
//
//            ) {
//
//        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(userViewModel: UserViewModel, onSignUpClick : () -> Unit, onLogin : () -> Unit) {

    var email by remember { userViewModel.email }
    var password by remember { userViewModel.password }

    var errorMsg by remember { userViewModel.errorMsg }
//    Row(
//        modifier = Modifier
//            .padding(24.dp)
//            .heightIn(24.dp),
//        horizontalArrangement = Arrangement.Center
//
//    ) {
//        Column(
//            modifier = Modifier
//                .weight(1f)
//        ) { // vertically
//            Text(text = "Email")
//            Spacer(modifier = Modifier.height(45.dp))
//            Text(text = "Password")
//        }

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxWidth().height(500.dp),
//            .padding(24.dp)
//            .heightIn(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
         // horizontalArrangement = Arrangement.Center

        ){
            Text(
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 60.dp, vertical = 20.dp)
                    .fillMaxWidth(),
//                textAlign = TextAlign.Start,
                text = stringResource(R.string.Login_Title),
                color = colorResource(id = R.color.PageTitlesColor),
                fontWeight = FontWeight.Bold
            )



            // not as the value before , negating the value
            TextField(value = email, onValueChange = { email = it},
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Enter your email")},
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            TextField(value = password, onValueChange = { password = it},
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Enter your password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )



            Row {
                Text(
                    text = stringResource(R.string.no_account)
                )

                //To nevigate the user to the sign up page
                Text(text = stringResource(R.string.singup),
                    color = colorResource(id = R.color.SignUp),
                    modifier = Modifier.clickable {

                        onSignUpClick()
                    }
                )

            }

//            Spacer(modifier = Modifier.width(20.dp))

            if(!userViewModel.bottomSheet.value){
                Text(
                    text = "Skip Login",
                    color = colorResource(id = R.color.SignUp),
                    modifier = Modifier.clickable {
                        onLogin()
                    },
                    textDecoration = TextDecoration.Underline
                )
            }


            LoginButton(
                "Login",
                onClickAction = {
                   val isLogedIn = userViewModel.login()
                    if (isLogedIn){
                        onLogin()
                    }

                }
            )


        }


            if (errorMsg.isNotEmpty()){
                ErrorMsgDialog(errorMsg, onDismiss = {errorMsg = ""},
                    modifier = Modifier.align(Alignment.Center
                    ))
            }
        }
//    }


}


@Composable
fun LoginButton(name: String, modifier: Modifier = Modifier, onClickAction: () -> Unit) {


    //hight , width
    Button(
        onClick = onClickAction, modifier = modifier
            .padding(0.5.dp)
    ) {
        Text(
            text = "$name"


            // Bold , color
        )
    }

}
