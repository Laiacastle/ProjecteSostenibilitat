package org.project.dietes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ViewUserStatistics(
    userId: Int,
    navViewModel: NavViewModel,
    usersViewModel: UsersDataViewModel = viewModel()
){
    val user = usersViewModel.getUserById(userId) ?: return
    Column(
    ){
        Text(user.name)
        Spacer(Modifier.height(10.dp))
        Text(user.lastName)
        Spacer(Modifier.height(10.dp))
        Text(user.email)
        Spacer(Modifier.height(10.dp))
        Text(user.weight.toString())
        Spacer(Modifier.height(10.dp))
        Text(user.exerciseDone)
        Spacer(Modifier.height(10.dp))
        Text(user.sleepTime.toString())
        Spacer(Modifier.height(10.dp))
        Text(user.age.toString())
        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                navViewModel.selectUserId = user.idUser
                navViewModel.navTo(Screen.EditUser)
            }
        ){
            Text("Modificar les dades")
        }
    }
}