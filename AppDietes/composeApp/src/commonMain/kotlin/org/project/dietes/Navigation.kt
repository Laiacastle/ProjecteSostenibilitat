package org.project.dietes
/*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.*
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
//import androidx.lifecycle.viewmodel.compose.viewModel


sealed interface Screen {
    data object Home : Screen
    data object Games : Screen
}
class NavViewModel : ViewModel(){
    val currentScreen = mutableStateOf<Screen>(Screen.Home)
    fun navTo(screen: Screen) {currentScreen.value = screen}
}


@OptIn(InternalComposeApi::class, ExperimentalMaterial3Api::class)
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
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("Menu")},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            /*Icon(
                                imageVector = ,
                                contentDescription = "Menu",
                            )*/
                        }
                    }
                )
            }
        ){ padding ->
            Box(modifier = Modifier.padding(padding)){
                when (currentScreen) {
                    Screen.Home -> HomePage() // Home Page
                    Screen.Games -> GamesScreen()
                }
            }
        }
    }
}



@Composable
fun HomePage() {
    TODO("Not yet implemented")
}
*/