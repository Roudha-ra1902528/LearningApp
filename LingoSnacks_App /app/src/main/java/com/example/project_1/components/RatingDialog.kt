package com.example.project_1.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_1.repo.Rating

import com.example.project_1.repo.RatingRepo
import com.example.project_1.screens.LoginForm
import com.example.project_1.viewModels.UserViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingDialog(
    onSignUp: () -> Unit,
    userViewModel: UserViewModel,
    onLogIn: () -> Unit,
    email: String,
    packageId: Int,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    onRatingSaved: (Float, String, String) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val existingRating = RatingRepo.findUserRating(packageId, email)
    var rating by remember { mutableStateOf(existingRating?.rating ?: 0F) }
    var comment by remember { mutableStateOf(existingRating?.comment ?: "") }
    val formattedDateTime = remember { SimpleDateFormat("dd MMM yyyy HH:mm").format(Date()) }

    if (userViewModel.isLogedIn()) {
        AlertDialog(
            icon = {
//            Icon(icon, contentDescription = "Example Icon")
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    StarRating(
                        modifier = Modifier.fillMaxWidth(),
                        maxRating = 5,
                        rating = rating,
                        onRatingChanged = { newRating ->
                            rating = newRating
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = comment,
                        onValueChange = { newComment -> comment = newComment },
                        label = {
                            Text("Comment")
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                Button(
                    onClick = {
                        onRatingSaved(rating, comment, formattedDateTime)
                        RatingRepo.addRating(
                            packageId,
                            Rating(email, comment, formattedDateTime, rating)
                        )
                        RatingRepo.packageRatings.forEach { println(it) }
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    } else {
        showBottomSheet = true
        LoginBottomSheet(
            onSignUp,
            onLogIn,
            onDismissRequest = {
                showBottomSheet = false
            },
            userViewModel = userViewModel
        )
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBottomSheet(
    onSignUp: () -> Unit,
    onLogIn: () -> Unit,
    onDismissRequest: () -> Unit,
    userViewModel: UserViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    userViewModel.bottomSheet.value = true

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Insert your LoginForum composable here
            LoginForm(userViewModel, onSignUp, onLogIn)

            // Button to hide the bottom sheet
//            Button(
//                onClick = {
//                    scope.launch {
//                        sheetState.hide()
//                    }.invokeOnCompletion {
//                        if (!sheetState.isVisible) {
//                            onDismissRequest()
//                        }
//                    }
//                }
//            ) {
//                Text("Hide bottom sheet")
//            }
        }
    }
}

@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    maxRating: Int,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    val stars = (1..maxRating).toList()
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stars.forEach { star ->
            val isFilled = star <= rating
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = if (isFilled) "Filled Star" else "Empty Star",
                tint = if (isFilled) Color(0xFFFFD700) else LocalContentColor.current,
                modifier = Modifier
                    .clickable { onRatingChanged(star.toFloat()) }
                    .size(32.dp)
            )
        }
    }
}
