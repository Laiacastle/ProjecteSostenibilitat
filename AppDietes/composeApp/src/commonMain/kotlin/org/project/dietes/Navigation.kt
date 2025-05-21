package org.project.dietes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Res
import appdietes.composeapp.generated.resources.Logo
import org.jetbrains.compose.resources.painterResource

sealed interface Screen {
    data object Home : Screen
    data object Games : Screen
    data object Account : Screen
    data object CreateUser : Screen
    data object EditUser : Screen
}
class NavViewModel : ViewModel(){
    val currentScreen = mutableStateOf<Screen>(Screen.Games)
    var selectUserId by mutableStateOf<Int?>(null)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.width(10.dp))
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(Res.drawable.Logo),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Menu")
                }
                NavigationDrawerItem(
                    label = { Text("HomePage") },
                    selected = false,
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home")},
                    onClick = {
                        navViewModel.navTo(Screen.Home)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Account") },
                    selected = false,
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account")},
                    onClick = {
                        navViewModel.navTo(Screen.Account)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Login") },
                    selected = false,
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account")},
                    onClick = {
                        navViewModel.navTo(Screen.CreateUser)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Games") },
                    selected = false,
                    icon = {Icon(Icons.Default.Games, contentDescription = "Games")},
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
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                            )
                        }
                    }
                )
            }
        ){ padding ->
            Box(modifier = Modifier.padding(padding)){
                when (currentScreen) {
                    Screen.Home -> HomePage() // Home Page
                    Screen.Games -> GamesScreen()
                    Screen.Account -> navViewModel.selectUserId?.let { userId ->
                        ViewUserStatistics(userId, navViewModel)
                    }        // Account Page
                    Screen.CreateUser -> CreateUserStatisticsScreen(
                        onAddUser = { user ->
                            navViewModel.navTo(Screen.Account)
                        },
                        onCancel = {
                            navViewModel.navTo(Screen.Games)
                        }
                    )
                    Screen.EditUser -> navViewModel.selectUserId?.let { userId ->
                        EditUserStatisticsScreen(userId, navViewModel,
                            onCancel = {
                                navViewModel.navTo(Screen.Account)
                            }
                        )
                    }
                    }
                }
            }
        }
    }

@Composable
fun AccountScreen() {
    TODO("Not yet implemented")
}

@Composable
fun HomePage() {
    TODO("Not yet implemented")
}