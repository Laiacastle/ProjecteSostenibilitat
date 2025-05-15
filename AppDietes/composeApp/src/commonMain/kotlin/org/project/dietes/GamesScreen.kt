package org.project.dietes

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    val description: String
)
class GamesViewModel : ViewModel() {
    val gamesD = mutableStateListOf<GameData>()
    init {
        gamesD.addAll(
            listOf(
                GameData(name = "joc1", number = 1, description = "prova"),
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
        Spacer(Modifier.height(20.dp))
        Text("Games", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(games) { game ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                        .border(
                            width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp)
                            //brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),= RectangleShape
                        )
                        .clickable(onClick = {}),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "${game.name}: Num ${game.number}, desc ${game.description}",
                        modifier = Modifier.padding(start = 10.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}