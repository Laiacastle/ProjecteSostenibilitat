package org.project.dietes.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DrawerValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FoodBank
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Res
import appdietes.composeapp.generated.resources.Logo
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.CreateUserStatisticsScreen
import org.project.dietes.DietScreen.DietSreen
import org.project.dietes.DietScreen.UserManager
import org.project.dietes.EditUserStatisticsScreen
import org.project.dietes.RecipesScreen.RecipesScreen
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

    val darkPink = Color(112, 65, 61)
    val pink = Color(228,213,221)

    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(pink)
                    .widthIn(max = 320.dp)
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.background(darkPink).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Spacer(Modifier.width(10.dp))
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(Res.drawable.Logo),
                        contentDescription = null,
                        tint = pink
                    )
                    Spacer(Modifier.width(20.dp))
                    Text("Dietes NoSeQue", color = pink)
                }
                NavigationDrawerItem(
                    label = { Text("Inici", color =darkPink) },
                    selected = false,
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = darkPink)},
                    onClick = {
                        navViewModel.navTo(Screen.Home)
                        scope.launch { drawerState.close() }
                    }
                )
                if(UserManager.getToken()!=null){
                    NavigationDrawerItem(

                        label = { Text("Compte", color = darkPink)},
                        selected = false,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Register" ,tint = darkPink)},
                        onClick = {
                            navViewModel.navTo(Screen.Account)
                            scope.launch { drawerState.close() }
                        }
                    )
                }

                NavigationDrawerItem(
                    label = { Text("Jocs", color = darkPink) },
                    selected = false,
                    icon = {Icon(Icons.Default.Games, contentDescription = "Games", tint = darkPink)},
                    onClick = {
                        navViewModel.navTo(Screen.Games)
                        scope.launch { drawerState.close() }
                    }
                )
                if(UserManager.getToken() == null){
                    NavigationDrawerItem(

                        label = { Text("Inicia Sessió", color = darkPink) },
                        selected = false,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account", tint = darkPink)},
                        onClick = {
                            navViewModel.navTo(Screen.LoginUser)
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Registrar-se", color = darkPink) },
                        selected = false,
                        icon = { Icon(Icons.Default.PersonAddAlt1, contentDescription = "Account", tint =darkPink)},
                        onClick = {
                            navViewModel.navTo(Screen.CreateUser)
                            scope.launch { drawerState.close() }
                        }
                    )
                }

                NavigationDrawerItem(

                    label = { Text("Les meves dietes", color = darkPink)},
                    selected = false,
                    icon = { Icon(Icons.Default.FoodBank, contentDescription = "Diets" ,tint = darkPink)},
                    onClick = {
                        navViewModel.navTo(Screen.Diets)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        Scaffold(
            containerColor = pink,
            topBar = {
                TopAppBar(
                    title = { Text("", color = darkPink) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = pink
                    ),
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
                                tint = darkPink
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (val screen = navViewModel.currentScreen.value) {
                    Screen.Home -> HomePageScreen(
                        navigateToLogInScreen = {navViewModel.navTo(Screen.LoginUser)},
                        navigateToGamesScreen = {navViewModel.navTo(Screen.Games)},
                        navigateToGamesResultsScreen = {},
                        navigateToAIDietesScreen = {},
                        navigateToDietesScreen = {navViewModel.navTo((Screen.Diets))}
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

                        },
                        navigateToScreenLogin = {navViewModel.navTo(Screen.LoginUser)}
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
                        navigateToScreenRegister = {navViewModel.navTo(Screen.CreateUser)},
                        navigateToScreenHome = {navViewModel.navTo(Screen.Home)},
                        onCancel = {
                            navViewModel.navTo(Screen.Home)
                        }
                    )
                    Screen.Diets -> DietSreen(
                        navigateToRecipeScreen = {id ->
                            navViewModel.navTo(Screen.Recipes(id))
                        }
                    )

                    is Screen.Recipes -> RecipesScreen(id = screen.id)
                }
            }
        }
    }
}