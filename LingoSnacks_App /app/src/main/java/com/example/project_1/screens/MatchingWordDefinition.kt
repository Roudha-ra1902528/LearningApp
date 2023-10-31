package com.example.project_1.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.project_1.model.Definition
import com.example.project_1.model.MatchingGameTrail
import com.example.project_1.model.Word



@Composable
fun WordDefinitiom_Card(definition: Definition,
                        modifier: Modifier = Modifier,
                        isSelected : Boolean,
                        onRadoClick : () -> Unit) {
//    read the image using a name [read a resource using a name]

    Card(

        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp).clickable { onRadoClick() },
        border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                containerColor =  MaterialTheme.colorScheme.onPrimary,

    )
    ) {
        Row(
            modifier = modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            Text(
                text = definition.text,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1F)
            )
//            Text(text = "Definition : ${word.definition}")

        RadioButton(selected = isSelected, onClick = onRadoClick)


        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchingWordGameTrial(trail : MatchingGameTrail, onRadoClick: (Int) -> Unit,
                          modifier: Modifier = Modifier
                          ) {

//    var selectedRadoIndex by remember { mutableStateOf(intialSection) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(
            text = trail.word.text,
            style = MaterialTheme.typography.titleLarge,
            textDecoration = TextDecoration.Underline
        )

        LazyColumn(
//            modifier = modifier,
        ) {
            itemsIndexed(trail.definitionAnswers){ index ,item->

                WordDefinitiom_Card(
                    item,
                    isSelected = index == trail.currentSelection,
                    onRadoClick = {
                      onRadoClick(index)
                    } ) // still uncomplete
            }
        }
    }
   
}


//@Preview
//@Composable
//fun StadiumCardPreview() {
//
//    val word = Word(
//        text = "Mall",
//        definition = listOf(" Place to do shooping"),
//        sentence = listOf("test", listOf<Resource>("test1", "test1", "photo"))
//
//    )
//     MaterialTheme {
//         WordDefinitiom_Card(word)
//     }
//}
////
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun StadiumList(stadiums: List<Stadium>, modifier: Modifier = Modifier) {
//    LazyVerticalStaggeredGrid(
//        modifier = modifier,
//        columns = StaggeredGridCells.Fixed(2)
//    ) {
//        items(stadiums) {
//            WordDefinitiom_Card(it)
//        }
//    }
//}
//
//@Preview
//@Composable
//fun StadiumListPreview() {
//    val stadiums = StadiumRepo.getStadiums(LocalContext.current)
//    StadiumsTheme {
//        StadiumList(stadiums)
//    }
//
//}


















































//@Composable
//fun MatchingWordDefiniton() {
//
//    Column (
//        horizontalAlignment = Alignment.CenterHorizontally,
//        //verticalArrangement = Arrangement.spacedBy(25.dp)
////        verticalArrangement = Arrangement.spacedBy(50.dp)
//
//    ) {
//        Header()
//        Word_DefinitionCard()
//    }
//}



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WordDefinitionArea( ) {
//
//    Column(
////        horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center,
//       modifier = Modifier.padding(8.dp),
//
//        horizontalAlignment = Alignment.CenterHorizontally,
//   //     verticalArrangement = Arrangement.spacedBy(25.dp)
//        verticalArrangement = Arrangement.SpaceEvenly
//
//         ) {
//
////        Row {
////            Column(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(8.dp, 8.dp),
//////                .weight(1f),
////                verticalArrangement = Arrangement.Top,
////                horizontalAlignment = Alignment.End
////            ) {
//////                Text(text = "Timer", modifier = Modifier.padding(16.dp,0.dp))
//////          OutlinedButton (modifier = Modifier){
//////              Text(text = "00:00")
//////          }
////
////                OutlinedButton(onClick = { } , modifier = Modifier
//////            .height(61.dp)
////                ) {
////                    Text(
////                        text = "00:00",  color = colorResource(id = R.color.PageTitlesColor),
////                        fontWeight = FontWeight.ExtraBold
////
////
////                        // Bold , color
////                    )
////                }
////            }
////        }
//
//
//        Row {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
////            modifier = Modifier.padding(0.dp,5.dp)
//            ) {
//                Text(
////                    text = "Level 1",
//                    text = stringResource(R.string.game_motivation),
//                    fontWeight = FontWeight.Bold,
//                    fontFamily = FontFamily.Monospace,
//                    style = MaterialTheme.typography.titleMedium,
//                    modifier =  Modifier.padding(0.dp, 8.dp)
//
//                    ) // will be changeable
////                Spacer(modifier = Modifier.padding(15.dp))
////                Text(
////
//////            textAlign = TextAlign.Center
////                )
//
//            }
//        }
//
////        Spacer(modifier = Modifier)
//
//
////
////        Row (
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(top = 16.dp, start = 8.dp),
////            horizontalArrangement = Arrangement.Start
////        ){
//////            Column(
//////                horizontalAlignment = Alignment.Start,
//////                verticalArrangement = Arrangement.SpaceBetween
//////            ) {
////
////                Text(text = "The Word is :")
////
//////            }
////        }
//3
//
//
//
//        Row{
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceEvenly,
//                modifier = Modifier.fillMaxHeight()
//            ) {
////                TextField(value = "", onValueChange = {},
////                    label = { Text(text = "Mall") },
////                    placeholder = { Text(text = "")},
////                    colors = TextFieldDefaults.outlinedTextFieldColors()
////                )
//                Text(
//                    text = "Mall",
//                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                )
//
////              Box(modifier = Modifier
////                      .fillMaxWidth()
////                  .padding(32.dp)
////              ){
////                  val items = listOf("Def1", "Def2", "3")
////                  var expanded by remember { mutableStateOf(false) }
////
//////                  ExposedDropdownMenuBox(  expanded = expanded,
//////                      onExpandedChange = {
//////                          expanded = !expanded
//////                      }
//////                  ) {
//////                      TextField(
//////                          value = "Test",
//////                          onValueChange = {},
//////                          readOnly = true,
//////                          trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//////                          modifier = Modifier.menuAnchor()
//////                      )
//////
//////                      ExposedDropdownMenu(
//////                          expanded = expanded,
//////                          onDismissRequest = { expanded = false }
//////                      ){
//////                          items.forEach { s ->
//////                              DropdownMenuItem(onClick = {}, text = {  Text(text = s )})
//////                          }
//////                      }
//////
//////                  }
////              }
//
//                Box(modifier = Modifier ){
//
//                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ,modifier = Modifier.heightIn(16.dp)
////                    , contentPadding = PaddingValues(horizontal = 16.dp)
//                ) {
//
////                    val dataList = listOf("Abhishek", "Harshit", "Gaurav")
////                    items(dataList) {it
////                    }
//                    items(DefItems){
//                            item -> DefElement(drawable = item, text = item)
//                    }
//
//                }
//
//                }
//
//
//
//
//
////        DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
////            Text(text = "Select")
////        }
//                Button(onClick = { /*TODO*/ }) {
//                    Column{
//                        // no should be changable
//                        Text(text = "Match words : 0")
//
//                        Text(text = "Unmatched words : 0")
//                    }
//
//                }
//
////
////        Button(onClick = { /*TODO*/ }) {
////            Column{
////                // no should be changable
////                Text(text = "Triangle --> to start")
////
////            }
////
////        }
//
//                //Size + Color
//                IconButton(onClick = { /*TODO*/ }) {
//
//                    //Icon --> painter , image
//                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null
//                    )
//                }
//
//                Text(text = "Click to enjoy")
//
//            }
//        }
//
//
//
//    }
//
//
//
//
//}
//
//@Composable
//fun DefElement(
//    modifier: Modifier = Modifier,
//    @DrawableRes drawable: Int,
//    @StringRes text: Int
//) {
//    Column( horizontalAlignment = Alignment.CenterHorizontally
//        , modifier = modifier) {
//
//        Image(painter = painterResource(drawable),
//            contentDescription = null,
//            contentScale = ContentScale.FillHeight,
//            modifier = Modifier
//                .size(150.dp)
////                .clip(CircleShape),
//
//
//            )
////        Text(text = stringResource(text),
////            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
////            style = MaterialTheme.typography.bodyMedium
////        )
//
//
////         Checkbox( onCheckedChange = {} , modifier = Modifier, checked = true  )
//
//
//        Checkbox(
//            checked = false,
//            onCheckedChange = {},
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//}
//
////Just to check
//private val DefItems = listOf(
//    R.drawable.xby4fbiv,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo,
//    R.drawable.logo
//)