package org.project.dietes.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NavViewModel : ViewModel(){
    val currentScreen = mutableStateOf<Screen>(Screen.Home)
    var selectUserId by mutableStateOf<String>("")
    fun navTo(screen: Screen) {currentScreen.value = screen}
}