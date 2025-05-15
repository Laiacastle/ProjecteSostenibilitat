package org.project.dietes

@Composable
fun GamesScreen(){
    val games = Int
    Column(
        Modifier.fillMaxSize(),
        horizontalALignment = Alignment.CenterHoritzontally
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Games", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(games) { game ->
                Row{
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                        .border(
                            width = 2.dp,
                            //brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                            shape = RectangleShape
                        )
                        .clickable(onCLick = onClick)
                    )
                }
            }
        }
    }
}