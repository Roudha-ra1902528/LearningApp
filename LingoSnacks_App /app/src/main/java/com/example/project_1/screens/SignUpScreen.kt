package com.example.project_1.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_1.R
import com.example.project_1.viewModels.UserViewModel



@Composable
fun SingUpScreen(onSubmit: () -> Unit, userViewModel: UserViewModel,modifier: Modifier = Modifier) {


    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(25.dp),
      modifier = modifier

    ) {

//        Header()



//
//
//        Column (
//            modifier = Modifier.heightIn(3.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(25.dp),
//
//            ){
////            Spacer(modifier = Modifier.height(1..dp))

        SignUpForm(onSubmit = onSubmit, userViewModel = userViewModel)

////            Spacer(modifier = Modifier.height(1.5.dp))
//            LoginButton(
//                "Sign Up",
//                onClickAction = { Log.d("Jameela", "Hello test") })
//


//        }
    }

}


//@Composable
//fun SignUpFormOld() {
//
//
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
//            Text(text = "First Name")
//            Spacer(modifier = Modifier.height(45.dp))
//            Text(text = "Last Name")
//            Spacer(modifier = Modifier.height(45.dp))
//            Text(text = "Email")
//            Spacer(modifier = Modifier.height(45.dp))
//            Text(text = "Password")
//            Spacer(modifier = Modifier.height(45.dp))
//            Text(text = "Photo URL")
//            Spacer(modifier = Modifier.height(45.dp))
//            Text(text = "role", ) // defult value
//
//        }
//
//
//
//        Column {
//
//
//            // not as the value before , negating the value
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " fill"
//                )
//
//            }
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " fill"
//                )
//
//            }
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " fill"
//                )
//
//            }
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " fill"
//                )
//
//            }
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " fill"
//                )
//
//            }
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " fill"
//                )
//
//            }
//            OutlinedButton(onClick = { }) {
//                Text(
//                    text = " defult Value"
//                )
//
//            }
//
//        }
//    }
//
//
//}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(userViewModel: UserViewModel, onSubmit : () -> Unit) {

    var firstName by remember { userViewModel.firstName }
    var lastName by remember { userViewModel.lastName }
    var email by remember { userViewModel.email }
    var password by remember { userViewModel.password }
    var photoUrl by remember { userViewModel.photoUrl }


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

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
//            .padding(24.dp)
//            .heightIn(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
            // horizontalArrangement = Arrangement.Center

        ){

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
//            textAlign = TextAlign.Center,
                text = stringResource(R.string.SignUp_Title),
                color = colorResource(id = R.color.PageTitlesColor),
                fontWeight = FontWeight.Bold

            )



            // not as the value before , negating the value
            TextField(value = firstName, onValueChange = {firstName = it},
                label = { Text(text = "First Name") },
                placeholder = { Text(text = "Enter your First Name")},
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            TextField(value = lastName, onValueChange = {lastName = it },
                label = { Text(text = "Last Name") },
                placeholder = { Text(text = "Enter your Last Name")},
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            TextField(value = email, onValueChange = {email = it},
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Enter your Email")},
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            TextField(value = password, onValueChange = {password = it},
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Enter your password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            TextField(value = photoUrl, onValueChange = {photoUrl = it},
                label = { Text(text = "Photo URL") },
                placeholder = { Text(text = "Enter your Photo URL")},
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            TextField(value = "Student", onValueChange = {},
                readOnly = true,
                label = { Text(text = "role") },
                placeholder = { Text(text = "Enter your role")},
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

//
//        Row {
//            Text(
//                text = stringResource(R.string.no_account)
//            )
//
//            //To nevigate the user to the sign up page
//            Text(text = stringResource(R.string.singup),
//                color = colorResource(id = R.color.SignUp),
//                modifier = Modifier.clickable { }
//            )
//
//        }

            SignUpButton(
                "Sign Up",
                onClickAction = {

                    val userSignedUp = userViewModel.signUpUser()
                    if (userSignedUp){
                        //home
                        onSubmit()
                    }

                })



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
fun SignUpButton(name: String, modifier: Modifier = Modifier, onClickAction: () -> Unit) {


    //hight , width
    Button(onClick = onClickAction, modifier = modifier
        .padding(0.5.dp)
    ) {
        Text(
            text = "$name"


            // Bold , color
        )
    }

}
