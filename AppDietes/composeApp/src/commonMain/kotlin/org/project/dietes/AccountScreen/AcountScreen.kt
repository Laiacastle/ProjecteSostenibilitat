package org.project.dietes.AccountScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import org.project.dietes.User.UserData

@Composable
fun UserProfileScreen(
    navigateToScreenLogin: () -> Unit,
    navigateToScreenHome: () -> Unit,
    navigateToEditScreen: () -> Unit
) {
    val vm = viewModel { AccountViewModel() }

    UserProfileContent(
        user = vm.user.value,
        navigateToScreenLogin = navigateToScreenLogin,
        logoutAndNavigate = {
            CoroutineScope(Dispatchers.Default).launch {
                vm.logout(navigateToScreenHome)
                delay(1000)
                navigateToScreenHome()
            }
        },
        navigateToEditScreen = navigateToEditScreen
    )
}

@Composable
fun UserProfileContent(
    user: UserData?,
    navigateToScreenLogin: () -> Unit,
    logoutAndNavigate: () -> Unit,
    navigateToEditScreen: () -> Unit
) {
    val background = Color(228, 213, 221)
    val green = Color(86, 165, 139)
    val darkPink = Color(112, 65, 61)
    val white = Color.White

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(background)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (user == null) {
                Button(
                    onClick = navigateToScreenLogin,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkPink,
                        contentColor = white
                    )
                ) {
                    Text("Encara no t'has loguejat!")
                }
            } else {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = white),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Foto de perfil",
                            tint = green,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color(240, 240, 240))
                                .padding(12.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "${user.name} ${user.surname}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = darkPink,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileItem("Usuari", user.userName)
                        ProfileItem("Email", user.email)
                        ProfileItem("Edat", "${user.age} anys")
                        ProfileItem("Pes", "${user.weight} kg")
                        ProfileItem("Exercici", user.exercise)
                        ProfileItem("Hores de son", "${user.hoursSleep} hrs")

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { navigateToEditScreen() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = green,
                                contentColor = white
                            )
                        ) {
                            Text("Editar Perfil")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = logoutAndNavigate,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = darkPink,
                                contentColor = background
                            )
                        ) {
                            Text("Logout")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
