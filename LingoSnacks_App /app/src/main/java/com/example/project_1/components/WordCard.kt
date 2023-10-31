package com.example.project_1.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WordCard(word: String, matched: Boolean = false){
    Card(
        border = BorderStroke(0.25.dp, Color.Black),
        modifier = Modifier
            .padding(16.dp),
        //set shape of the card
        shape = RoundedCornerShape(16.dp),
        content = {
            Text(word, modifier = Modifier.padding(16.dp),style = MaterialTheme.typography.labelLarge)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WordCardPreview(){
    WordCard("Placeholder")
}