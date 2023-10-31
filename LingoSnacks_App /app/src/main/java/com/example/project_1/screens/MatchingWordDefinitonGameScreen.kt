package com.example.project_1.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_1.R
import com.example.project_1.viewModels.MatchingGameViewModel
import java.time.format.TextStyle


@Composable
fun MatchingWordDefinitonGameScreen(
    viewModel : MatchingGameViewModel ,
    packageid : Int
    ,modifier: Modifier = Modifier
    ) {


//     viewModel.setPackageId(packageid)
    val trialList = remember {  viewModel.trials  }

    var trialNumber by remember { viewModel.trialNumber }
    var trial by remember { viewModel.trial }
//    var intialSection by remember { mutableStateOf(-1) }
    var correctAnswer by remember {   viewModel.correctAnswer}
    var inCorrectAnswer by remember {  viewModel.inCorrectAnswer}
    var showScore by remember {  viewModel.showScore}
    var showTrial by remember {  viewModel.showTrial}


    var nextButtonEnable by remember { viewModel.nextButtonEnabled }



    Column (
        modifier = modifier
    ){
//        Header()

        Spacer(modifier = Modifier.padding(8.dp))





        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text =viewModel.counterText,
                    textAlign = TextAlign.End
                    )
            }
            Spacer(modifier = Modifier.padding(32.dp))
            if (showTrial) {
                MatchingWordGameTrial(
                    trial,
                    onRadoClick = {
                        viewModel.enableNextButton()
                        viewModel.setCurrentSelection(it)
//                    trial.currentSelection = it
//                    trial = trial.copy( )
                    })
            }
            Spacer(modifier = Modifier.padding(16.dp))
            MatchResult(correctAnswer,inCorrectAnswer, showScore)
            
            Spacer(modifier = Modifier.padding(8.dp))
//            if (trialNumber )
            if (showTrial) {
                IconButton(
                    modifier = Modifier.size(150.dp),
                    onClick = {
                        viewModel.getNextTrial()
//               intialSection = -1
                    },
                    enabled = nextButtonEnable

                ) {

                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Next Word",
                        tint = if (nextButtonEnable)
                            colorResource(id = R.color.Play)
                        else
                            colorResource(id = R.color.Disable),
                        modifier = Modifier.size(150.dp)

                    )


                }
            }
            else{
                IconButton(
                    modifier = Modifier
                        .size(height = 150.dp, width = 290.dp)
                        .padding(top = 32.dp),
                    onClick = {
                        viewModel.resetGame()

//               intialSection = -1
                    },

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "Repeat", modifier = Modifier.size(50.dp))
                        Spacer(modifier = Modifier.padding(16.dp))
                        Text(text = "Play again with this package",  color = Color.Red)

                    }



                }
            }



        }

    }






}