package com.example.project_1.screens

import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_1.components.Tracker
import com.example.project_1.repo.PackageRepo
import com.example.project_1.viewModels.UnscrambleSentenceViewModel
import ui.dragdrop.DragTarget
import ui.dragdrop.DropTarget
import ui.dragdrop.LongPressDraggable

data class WordMatch(val word: String, val matched: Boolean = false)

//@Preview(showBackground = true)
//@Composable
//fun SuccessScreenPreview(){
//    SuccessScreen(3, 7, {})
//}

@Composable
fun SuccessScreen(score: Int, max : Int, navToHomeScreen: () -> Unit){



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        IconButton(
            onClick = navToHomeScreen,
            modifier = Modifier.padding(top = 90.dp).size(100.dp)
                .align(Alignment.TopStart) // Adjust the alignment as needed
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "", modifier = Modifier.size(50.dp))
        }
        Column (modifier = Modifier.height(400.dp), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally){
            Text(modifier = Modifier.padding(bottom = 40.dp), text = " You Are Done ! ", fontSize = 30.sp)
            GameStatus(score = score, max = max)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnscrambleSentenceScreen(
    viewModel: UnscrambleSentenceViewModel,
    packageId: Int,
    modifier: Modifier = Modifier,
    navToHomeScreen: () -> Unit
    ) {
    val context = LocalContext.current
    PackageRepo.initPackage(context)
    val pkg = PackageRepo.getPackage(packageId)
    val packageSentences = PackageRepo.getSentences(packageId)

//    var score by remember { viewModel.score }
    var scores = remember { viewModel.scores }

    var showSuccessScreen by remember { mutableStateOf(false) }
    var i by remember { mutableStateOf(0) }
    var sentence by remember { mutableStateOf(packageSentences.toMutableList()[0]) }

    Scaffold (
        topBar = {
            Row(modifier = Modifier.padding(top = 50.dp)){}
        }
    ){

        if(!showSuccessScreen) {
            Column(modifier = Modifier.padding(it)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
//                    Text(text = "${pkg.packageId} - ${pkg.title}", fontSize = 20.sp)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier.padding(bottom = 10.dp),
                        onClick = {
                            Log.d("SUCCESS","${i+1} and ${packageSentences.size}")
                            if(i+1 == packageSentences.size){
                                showSuccessScreen = !showSuccessScreen
                            }else{
                                sentence = packageSentences.toMutableList()[++i]
                            }

                        }) { Text(text = "Skip") }
                    Text(
                        text = "${i + 1} / ${packageSentences.size}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                MatchGame(packageId,i, scores[packageId], modifier, sentence, packageSentences.size, viewModel, {
                    sentence = packageSentences.toMutableList()[++i]
                }){
                    showSuccessScreen = !showSuccessScreen
                }
            }
        }else{
            SuccessScreen(scores[packageId], packageSentences.size, navToHomeScreen)
        }
    }
}

@Composable
fun MatchGame(packageId: Int, currentTrial: Int, score: Int, modifier: Modifier, sentence: String, max: Int, viewModel: UnscrambleSentenceViewModel,
              updateSentence: () -> Unit, showSuccess: () -> Unit) {

    var sentenceSplit = sentence?.split(" ") ?: mutableListOf()
    var words = sentenceSplit.map { WordMatch(it) }.toMutableList()
    var shuffledWords =  words.shuffled().toMutableStateList()

    LongPressDraggable(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DragList(shuffledWords.toMutableStateList())
            DropList(words.toMutableStateList(), onDrop = {
                var index = words.indexOfFirst { p -> p.word == it && !p.matched}

                if (index >= 0) words[index] = words[index].copy(matched = true)

                index = shuffledWords.indexOfFirst { p -> p.word == it && !p.matched}

                if (index >= 0) shuffledWords[index] = shuffledWords[index].copy(matched = true)

                if(shuffledWords.all { it.matched }) {
                    viewModel.trackScores("success",packageId)

                    print("$currentTrial and $max")
                    if(currentTrial+1 == max)
                        showSuccess()
                    else
                        updateSentence()
                }
            })

            GameStatus(score = score, max = max)

//            Button(onClick = {
//                for (idx in 0 until words.size) {
//                    words[idx] = words[idx].copy(matched = false)
//                    shuffledWords[idx] = shuffledWords[idx].copy(matched = false)
//                }
//            }) {
//                Text(
//                    text = "Reset", modifier = Modifier
//                        .fillMaxHeight(0.1f)
//                )
//            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DragList(words: List<WordMatch>) {
    FlowRow(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.Center //Arrangement.spacedBy(16.dp)
    ) {
        words.forEach { wordMatch ->
            key(wordMatch) {
                if (!wordMatch.matched) {
                    DragTarget(dataToDrop = wordMatch, modifier = Modifier.padding(6.dp)) {
                        SuggestionChip(onClick = { /* TODO */ },
                            label = { Text(text = wordMatch.word) })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DropList(words: List<WordMatch>, onDrop: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
        horizontalArrangement = Arrangement.Center //.spacedBy(8.dp)
    ) {
        words.forEach{

            DropTarget<WordMatch>(
                modifier = Modifier.padding(6.dp)
            ) { isInBound, word ->
                val bgColor = if (isInBound) Color.Yellow else Color.White


                if (word != null) {
                    println("""isInBound: $isInBound
                    |      ${word.word} == ${it.word}
                    |      """".trimMargin())
                    if (isInBound && word.word == it.word) {
                        onDrop(word.word)
                    }
                }

                DropCard(it, bgColor)

            }
        }
    }
}

@Composable
fun DropCard(word: WordMatch, bgColor: Color) {
    Column(modifier = Modifier
        .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
        .background(bgColor, RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = if(!word.matched) Modifier
                .size(width = 65.dp, height = 50.dp)
                .padding(top = 15.dp)
            else Modifier
                .height(50.dp)
                .padding(15.dp),
            textAlign = TextAlign.Center,
            text = if (word.matched) word.word else " "
        )
    }
}

@Composable
fun GameStatus(score: Int, max: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = " Score $score / $max",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

    }
}


@Preview(showSystemUi = true)
@Composable
fun UnscrambleSentenceScreenPreview() {

//    UnscrambleSentenceScreen(
//        null,
//        1,
//    )
}

// If list is empty -> show Snack bar and button to go next ->


//.border(0.5.dp,Color.Black)