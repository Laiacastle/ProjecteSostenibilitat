package org.project.dietes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

data class UserData(
    val idUser: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val weight: Float,
    val exerciseDone: String,
    val sleepTime: Float,
    val age: Int
)
class UserDataViewModel : ViewModel(){
    val users = mutableStateListOf<UserData>()
}
@Composable
fun UserStatisticsScreen(){
    val users = UserDataViewModel().users
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var nameError by remember { mutableStateOf(false) } // 1
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var weight by remember { mutableStateOf(TextFieldValue("")) }
    var exerciseDone by remember { mutableStateOf(TextFieldValue("")) }
    var sleepTime by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.height(10.dp))
        Text("Information User", fontSize = 20.sp)
        // name input
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Name")},
            isError = nameError,
            placeholder = {Text(text = "Enter your name")}
        )

        val assistiveElementText = if (nameError) "Error: Obligatorio" else "*Obligatorio" // 4
        val assistiveElementColor = if (nameError) { // 5
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(// 6
            text = assistiveElementText,
            color = assistiveElementColor,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(20.dp))
        // lastname input
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Last Name")},
            placeholder = {Text(text = "Enter your Last Name")}
        )
        Spacer(Modifier.height(20.dp))
        // email input
        OutlinedTextField(
            value = email,
            leadingIcon = {Icon(imageVector = Icons.Default.Email,
                contentDescription = null)},
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Enter your e-mail") }
        )
        Spacer(Modifier.height(20.dp))
        // weight input
        OutlinedTextField(
            value = weight,
            onValueChange = {
                weight = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text(text = "Weight")},
            placeholder = {Text(text = "Enter your Weight")}
        )
        Spacer(Modifier.height(20.dp))
        // exerciseDone input
        OutlinedTextField(
            value = exerciseDone,
            onValueChange = {
                exerciseDone = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Exercise Done")},
            placeholder = {Text(text = "Enter your Exercise Done")}
        )
        Spacer(Modifier.height(20.dp))
        // sleepTime input
        OutlinedTextField(
            value = sleepTime,
            onValueChange = {
                sleepTime = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text(text = "Sleep Time")},
            placeholder = {Text(text = "Enter your Sleep Time")}
        )
        Spacer(Modifier.height(20.dp))
        // age input
        OutlinedTextField(
            value = age,
            leadingIcon = {Icon(imageVector = Icons.Default.Cake,
                contentDescription = null)},
            onValueChange = {
                age = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {Text(text = "Age")},
            placeholder = {Text(text = "Enter your Age")}
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                //nameError = name.isBlank()
                /*users.add( // no va
                    UserData(
                        idUser = 1, // generated Auto no fet
                        name = name.toString(),
                        lastName = lastName.toString(),
                        email = email.toString(),
                        weight = weight.toString().toFloat(),
                        exerciseDone = exerciseDone.toString(),
                        sleepTime = sleepTime.toString().toFloat(),
                        age = age.toString().toInt(),
                    )
                )*/
                // bbdd
            }
        ){
            Text("Envia")
        }
        Text(text = name.toString())
        /*LazyColumn {
            items(users){ user ->
                Row {
                    Column {
                        Text(user.name)
                    }
                }
            }
        }*/
    }
}