package org.project.dietes.gamesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GamesScreen() {
    val viewModel  = viewModel { GamesViewModel() }
    GamesScreen(viewModel.gamesD, viewModel.color1, viewModel.color2, viewModel.color3, viewModel.color4, viewModel.color5)
}

@Composable
fun GamesScreen(games: List<GameData>, color1 : Color, color2 : Color, color3 : Color, color4 : Color, color5 : Color) {

    Column(
        Modifier
            .fillMaxSize()
            .background(color5)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(Modifier.height(40.dp))
        Text("Jocs", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = Color.Black)

        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(games) { game ->
                Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.horizontalGradient(listOf(color2,color3)),
                                shape = RoundedCornerShape(10.dp)
                            )
                            //.background() //image Url
                    ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ){
                    Column(
                        modifier = Modifier.padding(20.dp).align(Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = game.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black, // prova colors paleta
                            textAlign = TextAlign.Center,
                        )
                        Spacer(Modifier.height(20.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.textButtonColors(color3,color1), // prova colors
                        ){
                            Text("Jugar")
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}