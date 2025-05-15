package org.project.dietes

import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

data class GameData(
    val name: String,
    val number: Int,
    val description: String,
    val imageUrl: String,
)
class GamesViewModel : ViewModel() {
    val gamesD = mutableStateListOf<GameData>()
    init {
        gamesD.addAll(
            listOf(
                GameData(name = "joc1", number = 1, description = "prova", imageUrl = ""),
                GameData(name = "joc2", number = 5, description = "prova433", imageUrl = ""),
                GameData(name = "joc3", number = 19, description = "prova54", imageUrl = ""),
            )
        )
    }
}

@Composable
fun GamesScreen(){
    val games = GamesViewModel().gamesD

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(50.dp))
        Text("Games", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(games) { game ->
                Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            .border(
                                width = 2.dp,
                                brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                                shape = RoundedCornerShape(10.dp)
                            )
                            //.background() image Url
                    ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ){
                    Column(
                        modifier = Modifier.padding(20.dp).align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = game.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(red = 0x8E, green = 0xF4, blue = 0xC0), // prova colors paleta
                            textAlign = TextAlign.Center,
                        )
                        Spacer(Modifier.height(20.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.textButtonColors(),
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