package org.project.dietes.navigation

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
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.*
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Res
import appdietes.composeapp.generated.resources.Logo
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.homePage.HomePageScreen

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
                    icon = { Icon(Icons.Default.PersonAddAlt1, contentDescription = "Account")},
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
                    Screen.Home -> HomePageScreen(
                        navigateToModifyInformationScreen = {navViewModel.navTo(Screen.EditUser)},
                        navigateToGamesScreen = {navViewModel.navTo(Screen.Games)},
                        navigateToGamesResultsScreen = {},
                        navigateToAIDietesScreen = {},
                        navigateToDietesScreen = {}
                    ) // Home Page
                    Screen.Games -> _root_ide_package_.org.project.dietes.GamesScreen()
                    Screen.Account -> navViewModel.selectUserId?.let { userId ->
                        _root_ide_package_.org.project.dietes.ViewUserStatistics(userId, navViewModel)
                    }
                    Screen.CreateUser -> _root_ide_package_.org.project.dietes.CreateUserStatisticsScreen(
                        onAddUser = { user ->
                            navViewModel.navTo(Screen.Account)
                        },
                        onCancel = {
                            navViewModel.navTo(Screen.Games)
                        }
                    )
                    Screen.EditUser -> navViewModel.selectUserId?.let { userId ->
                        _root_ide_package_.org.project.dietes.EditUserStatisticsScreen(
                            userId, navViewModel,
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