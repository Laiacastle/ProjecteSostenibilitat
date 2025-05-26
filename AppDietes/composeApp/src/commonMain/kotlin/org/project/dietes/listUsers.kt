package org.project.dietes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

// arxiu prova
@Composable
fun ListUsers() {
    val users = UsersDataViewModel().users.value
    if (users == null){
        Text("nada")
    } else{

    LazyColumn {
        items(users.toList()) { user ->
            Row {
                Column {
                    Text(user.name)
                    Text(user.surname)
                    Text(user.email)
                    Text(user.weight.toString())
                    Text(user.exercise)
                    Text(user.hoursSleep.toString())
                    Text(user.age.toString())
                }
            }
        }
    }
}
}