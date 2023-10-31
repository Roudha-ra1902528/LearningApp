package com.example.project_1.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ErrorMsgDialog(errorMsg :String, onDismiss : () -> Unit, modifier: Modifier = Modifier) {
    Card (
        //modify
        modifier = modifier.size(width = 350.dp , height = 150.dp),
        border = BorderStroke(1.dp, Color.Black),

    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = errorMsg)
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedButton(onClick =  onDismiss ) {
                Text(text = "OK")
            }
        }
    }


}