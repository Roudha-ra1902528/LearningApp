package com.example.project_1.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.project_1.R

//modifier add
@Composable
fun MatchResult( correct : Int, inCorrect: Int, showTotalScore : Boolean) {

 Card (

         modifier = Modifier.size(220.dp, 98.dp).border(1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp) ),
//         shape = MaterialTheme.shapes.medium,
//         shape = RoundedCornerShape(16.dp),
         // modifier = modifier.size(280.dp, 240.dp)

         //set card elevation of the card
         elevation = CardDefaults.cardElevation(
             defaultElevation =  10.dp,
         ),
         colors = CardDefaults.cardColors(
             containerColor =  MaterialTheme.colorScheme.onPrimary,
         )


 ){
     Column (
         modifier = Modifier.padding(start = 16.dp, top = 16.dp),
         horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
         Text(text = " Correct Matches :  ${correct}", color = colorResource(id = R.color.SignUp)  )
         Text(text =" inCorrect Matches  :  ${inCorrect}", color = colorResource(id = R.color.SignUp)  )

         if (showTotalScore){
             Text(text ="Your Score  :  ${correct}/${correct + inCorrect}", color = colorResource(id = R.color.SignUp)  )
         }
     }
 }




}