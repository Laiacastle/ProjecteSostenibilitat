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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditUserStatisticsScreen(
    userId: Int,
    navViewModel: NavViewModel,
    usersViewModel: UsersDataViewModel = viewModel()
){
    val user = usersViewModel.getUserById(userId) ?: return
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf("") }
    var lastNameError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var weight by remember { mutableStateOf("") }
    var weightError by remember { mutableStateOf(false) }
    var weightErrorNum by remember { mutableStateOf(false) }
    var exerciseDone by remember { mutableStateOf("") }
    var exerciseDoneError by remember { mutableStateOf(false) }
    var sleepTime by remember { mutableStateOf("") }
    var sleepTimeError by remember { mutableStateOf(false) }
    var sleepTimeErrorNum by remember { mutableStateOf(false) }
    var age by remember { mutableStateOf("") }
    var ageError by remember { mutableStateOf(false) }
    var ageErrorNum by remember { mutableStateOf(false) }

    val color1 = Color(red = 0x8E, green = 0xF4, blue = 0xC0)
    val color2 = Color(red = 0x56, green = 0xA5, blue = 0x8B)
    val color3 = Color(red = 0x49, green = 0x60, blue = 0x5E)
    val color4 = Color(red = 0xB4, green = 0x84, blue = 0x80)
    val color5 = Color(red = 0xE4, green = 0xD5, blue = 0xDD)
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
            Text("Information User", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(10.dp))
        // name input
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Name")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,

                ),
            isError = nameError,
            placeholder = {Text(user.name)}
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = lastNameError,
            placeholder = {Text(user.lastName)}
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = emailError,
            placeholder = { Text(user.email) }
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = weightError or weightErrorNum,
            placeholder = {Text(user.weight.toString())}
        )
        val assistiveElementTextWeight = if (weightError) "Error: Obligatorio" else if (weightErrorNum) "Error: Tenen que ser numeros"
        else "*Obligatorio"
        val assistiveElementColorWeight = if (weightError or weightErrorNum) {
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = exerciseDoneError,
            singleLine = true,
            placeholder = {Text(user.exerciseDone)}
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = sleepTimeError or sleepTimeErrorNum,
            placeholder = {Text(user.sleepTime.toString())}
        )
        val assistiveElementTextSleep = if (sleepTimeError) "Error: Obligatorio" else if (sleepTimeErrorNum) "Error: Tenen que ser numeros"
        else "*Obligatorio"
        val assistiveElementColorSleep = if (sleepTimeError or sleepTimeErrorNum) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium) }
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = ageError or ageErrorNum,
            placeholder = {Text(user.age.toString())}
        )
        val assistiveElementTextAge = if (ageError) "Error: Obligatorio" else if (ageErrorNum) "Error: Tenen que ser numeros"
        else "*Obligatorio"
        val assistiveElementColorAge = if (ageError or ageErrorNum) {
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
                weightErrorNum = !isDecimal(weight)
                sleepTimeErrorNum = !isDecimal(sleepTime)
                ageErrorNum = !isNumeric(age)

                if (!nameError and
                    !lastNameError and
                    !emailError and
                    !weightError and
                    !exerciseDoneError and
                    !sleepTimeError and
                    !ageError and
                    isDecimal(weight) and
                    isDecimal(sleepTime) and
                    isNumeric(age)) {

                    usersViewModel.updateUser(user.copy(
                        name = name,
                        lastName = lastName,
                        email = email,
                        weight = weight.toFloat(),
                        exerciseDone = exerciseDone,
                        sleepTime = sleepTime.toFloat(),
                        age = age.toInt(),))
                }
                navViewModel.navTo(Screen.Account)
            },
            colors = ButtonDefaults.textButtonColors(color1,color3)
        ){
            Text("Envia")
        }
        /*LazyColumn { // proves userData class mostrar dades inserides
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