package org.project.dietes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import appdietes.composeapp.generated.resources.eye_off_outline
import appdietes.composeapp.generated.resources.eye_outline
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.navigation.NavViewModel
import org.project.dietes.navigation.Screen

@Composable
fun UserLoginScreen(
    viewModel: UsersDataViewModel = viewModel(),
    onCancel: () -> Unit,
    navViewModel: NavViewModel = viewModel(),
) {
    val users = UsersDataViewModel().users
    val userId = 0
    var password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var dadesError by remember { mutableStateOf(false) }
    val color1 = Color(red = 0x8E, green = 0xF4, blue = 0xC0)
    val color2 = Color(red = 0x56, green = 0xA5, blue = 0x8B)
    val color3 = Color(red = 0x49, green = 0x60, blue = 0x5E)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(Res.drawable.Logo),
                contentDescription = "logo"
            )
            Spacer(Modifier.width(10.dp))
            Text("Inicia Sessió", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            onValueChange = {
                email = it
                dadesError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = "Adreça Electrònica") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = dadesError,
            placeholder = { Text(text = "Introdueix el teu correu electronic") }
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it
                            dadesError = false},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = dadesError,
            placeholder = { Text(text = "Introdueix la teva contrasenya") },
            visualTransformation =
                if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { hidden = !hidden }) {
                    val vector = painterResource(
                        if (hidden) Res.drawable.eye_outline
                        else Res.drawable.eye_off_outline
                    )
                    val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña"
                    Icon(painter = vector, contentDescription = description)
                }
            }
        )
        Spacer(Modifier.height(20.dp))
        if (dadesError){
            Text("Dades incorrectes")
        }
        TextButton(onClick = {}){
            Text("¿Encara no estas registrat?")
        }
        Row {
            Button(
                onClick = {
                    val user = viewModel.getUserByEmail(email)
                    dadesError = user?.password != password

                    if (!dadesError){
                        navViewModel.selectUserId = user?.idUser
                        navViewModel.navTo(Screen.Account)
                    }
                },
                colors = ButtonDefaults.textButtonColors(color1,color3)
            ){
                Text("Inicia Sessió")
            }
            Spacer(Modifier.width(10.dp))
            Button(
                onClick = {onCancel() },
                colors = ButtonDefaults.textButtonColors(Color.Red,color3)
            ){
                Text("Cancel·la")
            }
        }
    }
}