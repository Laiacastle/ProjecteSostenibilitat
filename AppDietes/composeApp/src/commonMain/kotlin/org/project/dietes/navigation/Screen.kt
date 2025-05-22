package org.project.dietes.navigation

sealed interface Screen {
    data object Home : Screen
    data object Games : Screen
    data object Account : Screen
    data object CreateUser : Screen
    data object EditUser : Screen
}