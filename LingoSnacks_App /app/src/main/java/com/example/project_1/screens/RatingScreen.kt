package com.example.project_1.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.project_1.model.User

@Composable
fun RatingScreen(currentUser: MutableState<User?>, package_id: Int) {
    Text(text = "Rating Screen $currentUser")
}