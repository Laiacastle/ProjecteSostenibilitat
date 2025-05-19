package org.project.dietes

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch


sealed interface Screen {
    data object Home : Screen
    data object Games : Screen
}
class NavViewModel : ViewModel(){
    val currentScreen = mutableStateOf<Screen>(Screen.Home)
    fun navTo(screen: Screen) {currentScreen.value = screen}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navViewModel = viewModel { NavViewModel() }
    val currentScreen = navViewModel.currentScreen.value
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet() {
                Text("Menu")
                NavigationDrawerItem(
                    label = { Text("HomePage") },
                    selected = false,
                    icon = { },
                    onClick = {
                        navViewModel.navTo(Screen.Home)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Games") },
                    selected = false,
                    icon = {},
                    onClick = {
                        navViewModel.navTo(Screen.Games)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ){}
}
