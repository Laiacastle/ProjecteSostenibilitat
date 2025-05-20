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
import androidx.compose.material.Button
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
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf("") }
    var lastNameError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var weight by remember { mutableStateOf("") }
    var weightError by remember { mutableStateOf(false) }
    var exerciseDone by remember { mutableStateOf("") }
    var exerciseDoneError by remember { mutableStateOf(false) }
    var sleepTime by remember { mutableStateOf("") }
    var sleepTimeError by remember { mutableStateOf(false) }
    var age by remember { mutableStateOf("") }
    var ageError by remember { mutableStateOf(false) }

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
        val assistiveElementText = if (nameError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColor = if (nameError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementText,
            color = assistiveElementColor,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        // lastname input
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
                lastNameError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Last Name")},
            isError = lastNameError,
            placeholder = {Text(text = "Enter your Last Name")}
        )
        val assistiveElementTextLastName = if (lastNameError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColorLastName = if (lastNameError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextLastName,
            color = assistiveElementColorLastName,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        // email input
        OutlinedTextField(
            value = email,
            leadingIcon = {Icon(imageVector = Icons.Default.Email,
                contentDescription = null)},
            onValueChange = {
                email = it
                emailError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = "Email address") },
            isError = emailError,
            placeholder = { Text(text = "Enter your e-mail") }
        )
        val assistiveElementTextEmail = if (emailError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColorEmail = if (emailError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextEmail,
            color = assistiveElementColorEmail,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        // weight input
        OutlinedTextField(
            value = weight,
            onValueChange = {
                weight = it
                weightError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text(text = "Weight")},
            isError = weightError,
            placeholder = {Text(text = "Enter your Weight")}
        )
        val assistiveElementTextWeight = if (weightError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColorWeight = if (weightError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextWeight,
            color = assistiveElementColorWeight,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        // exerciseDone input
        OutlinedTextField(
            value = exerciseDone,
            onValueChange = {
                exerciseDone = it
                exerciseDoneError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Exercise Done")},
            isError = exerciseDoneError,
            placeholder = {Text(text = "Enter your Exercise Done")}
        )
        val assistiveElementTextExercise = if (exerciseDoneError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColorExercise = if (exerciseDoneError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextExercise,
            color = assistiveElementColorExercise,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        // sleepTime input
        OutlinedTextField(
            value = sleepTime,
            onValueChange = {
                sleepTime = it
                sleepTimeError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text(text = "Sleep Time")},
            isError = sleepTimeError,
            placeholder = {Text(text = "Enter your Sleep Time")}
        )
        val assistiveElementTextSleep = if (sleepTimeError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColorSleep = if (sleepTimeError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextSleep,
            color = assistiveElementColorSleep,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        // age input
        OutlinedTextField(
            value = age,
            leadingIcon = {Icon(imageVector = Icons.Default.Cake,
                contentDescription = null)},
            onValueChange = {
                age = it
                ageError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {Text(text = "Age")},
            isError = ageError,
            placeholder = {Text(text = "Enter your Age")}
        )
        val assistiveElementTextAge = if (ageError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColorAge = if (ageError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextAge,
            color = assistiveElementColorAge,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                nameError = name.isBlank()
                lastNameError = lastName.isBlank()
                emailError = email.isBlank()
                weightError = weight.isBlank()
                exerciseDoneError = exerciseDone.isBlank()
                sleepTimeError = sleepTime.isBlank()
                ageError = age.isBlank()

                // add user
                users.add(
                    UserData(
                        idUser = 1, // generated Auto no fet
                        name = name,
                        lastName = lastName,
                        email = email,
                        weight = weight.toFloat(),
                        exerciseDone = exerciseDone,
                        sleepTime = sleepTime.toFloat(),
                        age = age.toInt(),
                    )
                )
            }
        ){
            Text("Envia")
        }
        /*LazyColumn {
            items(users){ user ->
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
        }*/
    }
}