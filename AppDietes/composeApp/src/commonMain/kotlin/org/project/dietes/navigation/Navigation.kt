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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Res
import appdietes.composeapp.generated.resources.Logo
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.CreateUserStatisticsScreen
import org.project.dietes.EditUserStatisticsScreen
import org.project.dietes.UserLoginScreen
import org.project.dietes.ViewUserStatistics
import org.project.dietes.gamesScreen.GamesScreen
import org.project.dietes.homePage.HomePageScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){

    val navViewModel = viewModel { NavViewModel() }
    val currentScreen = navViewModel.currentScreen.value
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
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
                    label = { Text("Inici") },
                    selected = false,
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home")},
                    onClick = {
                        navViewModel.navTo(Screen.Home)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(

                    label = { Text("Compte") },
                    selected = false,
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account")},
                    onClick = {
                        navViewModel.navTo(Screen.Account)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Crear Compte") },
                    selected = false,
                    icon = { Icon(Icons.Default.PersonAddAlt1, contentDescription = "Account")},
                    onClick = {
                        navViewModel.navTo(Screen.CreateUser)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Jocs") },
                    selected = false,
                    icon = {Icon(Icons.Default.Games, contentDescription = "Games")},
                    onClick = {
                        navViewModel.navTo(Screen.Games)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(

                    label = { Text("Inicia Sessió") },
                    selected = false,
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account")},
                    onClick = {
                        navViewModel.navTo(Screen.LoginUser)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Menu") },
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
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (currentScreen) {
                    Screen.Home -> HomePageScreen(
                        navigateToCreateUserScreen = {navViewModel.navTo(Screen.CreateUser)},
                        navigateToLogInScreen = {navViewModel.navTo(Screen.LoginUser)},
                        navigateToVewStatisticsScreen = {navViewModel.navTo(Screen.Account)},
                        navigateToModifyInformationScreen = {navViewModel.navTo(Screen.EditUser)},
                        navigateToGamesScreen = {navViewModel.navTo(Screen.Games)},
                        navigateToGamesResultsScreen = {},
                        navigateToAIDietesScreen = {},
                        navigateToDietesScreen = {}
                    ) // Home Page
                    Screen.Games -> GamesScreen()

                    Screen.Account -> navViewModel.selectUserId?.let { userId ->
                        ViewUserStatistics(userId, navViewModel)
                    }

                    Screen.CreateUser -> CreateUserStatisticsScreen(
                        onAddUser = { user ->
                            navViewModel.navTo(Screen.Account)
                        },
                        onCancel = {
                            navViewModel.navTo(Screen.Home)
                        }
                    )
                    Screen.EditUser -> navViewModel.selectUserId?.let { userId ->
                        EditUserStatisticsScreen(
                            userId, navViewModel,
                            onCancel = {
                                navViewModel.navTo(Screen.Account)
                            }
                        )
                    }
                    Screen.LoginUser -> UserLoginScreen(
                        onCancel = {
                            navViewModel.navTo(Screen.Home)
                        }
                    )
                }
            }
        }
    }
}