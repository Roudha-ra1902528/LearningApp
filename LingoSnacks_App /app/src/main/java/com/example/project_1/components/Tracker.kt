package com.example.project_1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_1.model.LearningPackage

@Composable
fun Tracker(current: Int, max: Int){
    Row(modifier = Modifier.fillMaxWidth().padding(30.dp),
        horizontalArrangement = Arrangement.End){
        Text(text = "$current / $max", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}