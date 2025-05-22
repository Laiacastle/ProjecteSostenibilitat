package org.project.dietes.gamesScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class GamesViewModel : ViewModel() {
    val color1 = Color(red = 0x8E, green = 0xF4, blue = 0xC0)
    val color2 = Color(red = 0x56, green = 0xA5, blue = 0x8B)
    val color3 = Color(red = 0x49, green = 0x60, blue = 0x5E)
    val color4 = Color(red = 0xB4, green = 0x84, blue = 0x80)
    val color5 = Color(red = 0xE4, green = 0xD5, blue = 0xDD)
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