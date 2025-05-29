package org.project.dietes.User

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
import androidx.compose.material3.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import appdietes.composeapp.generated.resources.Res
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.eye_off_outline
import appdietes.composeapp.generated.resources.eye_outline
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.DietScreen.green
import org.project.dietes.Token.TokenManager
import org.project.dietes.navigation.NavViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun EditUserScreen(

    onCancel: () -> Unit,
    navigateToScreenAccount : () -> Unit,
){
    val userId = Uuid.random().toString()  // generar automaticament
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
    var password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }
    var passwordError by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("UserName") } // falta implementar
    var userNameError by remember { mutableStateOf(false) }
    var diet by remember { mutableStateOf("") } // falta implementar
    var dietError by remember { mutableStateOf(false) }


    val viewModel = viewModel {UsersVM()}
    val color1 = Color(red = 0x8E, green = 0xF4, blue = 0xC0)
    val color2 = Color(red = 0x56, green = 0xA5, blue = 0x8B)
    val color3 = Color(red = 0x49, green = 0x60, blue = 0x5E)
    val darkPink = Color(112, 65, 61)
    val pink = Color(228,213,221)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
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
            Text("Modifica les teves dades", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        //Spacer(Modifier.height(10.dp))
        // name input
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Nom")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,

                ),
            isError = nameError,
            placeholder = {Text(text = "Introdueix el teu nom")}
        )
        val assistiveElementText = if (nameError) "Error: Obligatori" else "*Obligatori"
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

        Spacer(Modifier.height(2.dp))
        // lastname input
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
                lastNameError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Cognoms")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = lastNameError,
            placeholder = {Text(text = "Introdueix el teu cognom")}
        )
        val assistiveElementTextLastName = if (lastNameError) "Error: Obligatori" else "*Obligatori"
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

        Spacer(Modifier.height(2.dp))

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
            label = { Text(text = "Adreça Electrònica") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = emailError,
            placeholder = { Text(text = "Introdueix el teu correu electronic") }
        )
        val assistiveElementTextEmail = if (emailError) "Error: Obligatori" else "*Obligatori"
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

        Spacer(Modifier.height(2.dp))

        // weight input
        OutlinedTextField(
            value = weight,
            onValueChange = {
                weight = it
                weightError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text(text = "Pes")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = weightError or weightErrorNum,
            placeholder = {Text(text = "Introdueix el teu pes")}
        )
        val assistiveElementTextWeight = if (weightError) "Error: Obligatori" else if (weightErrorNum) "Error: Tenen que ser numeros"
        else "*Obligatori"
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

        Spacer(Modifier.height(2.dp))

        // exerciseDone input
        OutlinedTextField(
            value = exerciseDone,
            onValueChange = {
                exerciseDone = it
                exerciseDoneError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text = "Exercici Fet")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = exerciseDoneError,
            singleLine = true,
            placeholder = {Text(text = "Introdueix el exercici")}
        )
        val assistiveElementTextExercise = if (exerciseDoneError) "Error: Obligatori" else "*Obligatori"
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

        Spacer(Modifier.height(2.dp))

        // sleepTime input
        OutlinedTextField(
            value = sleepTime,
            onValueChange = {
                sleepTime = it
                sleepTimeError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {Text(text = "Horas de Son")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = sleepTimeError or sleepTimeErrorNum,
            placeholder = {Text(text = "Introdueix les hores de són")}
        )
        val assistiveElementTextSleep = if (sleepTimeError) "Error: Obligatori" else if (sleepTimeErrorNum) "Error: Tenen que ser numeros"
        else "*Obligatori"
        val assistiveElementColorSleep = if (sleepTimeError or sleepTimeErrorNum) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium) }
        Text(
            text = assistiveElementTextSleep,
            color = assistiveElementColorSleep,
            style = MaterialTheme.typography.caption,
        )

        Spacer(Modifier.height(2.dp))

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
            label = {Text(text = "Edat")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = ageError or ageErrorNum,
            placeholder = {Text(text = "Introdueix la teva edat")}
        )
        val assistiveElementTextAge = if (ageError) "Error: Obligatori" else if (ageErrorNum) "Error: Tenen que ser numeros"
        else "*Obligatori"
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

        Spacer(Modifier.height(2.dp))

        // password input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it
                passwordError = false},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = { Text("Contrasenya") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedIndicatorColor = color2,
                unfocusedIndicatorColor = color2,
            ),
            isError = passwordError,
            placeholder = { Text(text = "Introdueix la teva contrasenya") },
            visualTransformation =
                if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { hidden = !hidden }) {
                    val vector = painterResource(
                        if (hidden) Res.drawable.eye_outline
                        else Res.drawable.eye_off_outline
                    )
                    val description = if (hidden) "Ocultar contrasenya" else "Revelar contrasenya"
                    Icon(painter = vector, contentDescription = description)
                }
            }
        )
        val assistiveElementTextPassword = if (passwordError) "Error: Obligatori" else "*Obligatori"
        val assistiveElementColorPassword = if (passwordError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
        Text(
            text = assistiveElementTextPassword,
            color = assistiveElementColorPassword,
            style = MaterialTheme.typography.caption,
        )
        Spacer(Modifier.height(5.dp))
        TextButton(onClick = {navigateToScreenAccount()}){
            Text("¿Ja tens compte?", color = Color.Blue)
        }
        Spacer(Modifier.height(5.dp))
        if (viewModel.hasTriedUpdate.value ==true && viewModel.updateSucces.value==false) {
            Text("Format de contrasenya o email incorrectes", color = darkPink)
        }else if (viewModel.updateSucces.value ==true){
            Text("update succes! redirecting...", color = green)
            LaunchedEffect(Unit){
                delay(2000)
                navigateToScreenAccount()
            }
        }
        Spacer(Modifier.height(5.dp))
        Row{
            Button(
                onClick = { onCancel()  },
                colors = ButtonDefaults.textButtonColors(darkPink,pink)
            ){
                Text("Cancel·la")
            }
            Spacer(Modifier.width(5.dp))
            Button(
                onClick = {
                    nameError = name.isBlank()
                    lastNameError = lastName.isBlank()
                    emailError = email.isBlank()
                    weightError = weight.isBlank()
                    exerciseDoneError = exerciseDone.isBlank()
                    sleepTimeError = sleepTime.isBlank()
                    ageError = age.isBlank()
                    passwordError = password.isBlank()
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
                        !passwordError and
                        isDecimal(weight) and
                        isDecimal(sleepTime) and
                        isNumeric(age)
                    ) {

                        // add user
                        val newUser = UserData(
                            id = userId,
                            name = name,
                            surname = lastName,
                            email = email,
                            weight = weight.toDouble(),
                            exercise = exerciseDone,
                            hoursSleep = sleepTime.toDouble(),
                            age = age.toInt(),
                            password = password,
                            userName = name,
                            diet = null
                        )
                        viewModel.updateUser(viewModel.userId.toString(),newUser )

                    }

                },
                colors = ButtonDefaults.textButtonColors(color1,color3)
            ){
                Text("Envia")
            }


        }
    }
}