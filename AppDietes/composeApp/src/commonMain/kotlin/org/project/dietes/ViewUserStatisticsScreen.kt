package org.project.dietes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.navigation.NavViewModel
import org.project.dietes.navigation.Screen


@Composable
fun ViewUserStatistics(
    userId: Int,
    navViewModel: NavViewModel,
    usersViewModel: UsersDataViewModel = viewModel()
){
    val user = usersViewModel.getUserById(userId) ?: return
    val color1 = Color(red = 0x8E, green = 0xF4, blue = 0xC0)
    val color3 = Color(red = 0x49, green = 0x60, blue = 0x5E)
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 10.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(Res.drawable.Logo),
                contentDescription = "logo"
            )
            Spacer(Modifier.width(15.dp))
            Text(text = "Hola, ${user.name} ${user.lastName}", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(15.dp))
        Text(text = "Dades Usuari:", fontSize = 20.sp)
        Spacer(Modifier.height(15.dp))
        Text(text = "Nom: ${user.name}")
        Spacer(Modifier.height(10.dp))
        Text(text = "Cognoms: ${user.lastName}")
        Spacer(Modifier.height(10.dp))
        Text(text = "Correu: ${user.email}")
        Spacer(Modifier.height(10.dp))
        Text(text = "Pes: ${user.weight.toString()}")
        Spacer(Modifier.height(10.dp))
        Text(text = "Exercici Fet: ${user.exerciseDone}")
        Spacer(Modifier.height(10.dp))
        Text(text = "Hores de Son: ${user.sleepTime.toString()}")
        Spacer(Modifier.height(10.dp))
        Text(text = "Edat: ${user.age.toString()}")
        Spacer(Modifier.height(15.dp))

        Button(
            onClick = {
                navViewModel.selectUserId = user.idUser
                navViewModel.navTo(Screen.EditUser)
            },
            colors = ButtonDefaults.textButtonColors(color1,color3)

        ){
            Text("Modificar les dades")
        }
    }
}