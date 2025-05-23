package org.project.dietes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// arxiu prova
@Composable
fun ListUsers() {
    val users = UsersDataViewModel().users
    LazyColumn {
        items(users.value!!.toMutableList()) { user ->
            Row {
                Column {
                    Text(user.name)
                    Text(user.lastName)
                    Text(user.email)
                    Text(user.weight.toString())
                    Text(user.exerciseDone)
                    Text(user.sleepTime.toString())
                    Text(user.age.toString())
                }
            }
        }
    }
}