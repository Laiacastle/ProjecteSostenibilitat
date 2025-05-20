package org.project.dietes.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomePageScreen(
    navigateToModifyInformationScreen: () -> Unit,
    navigateToGamesScreen: () -> Unit,
    navigateToGamesResultsScreen: () -> Unit,
    navigateToAIDietesScreen: () -> Unit,
    navigateToDietesScreen: () -> Unit
    ) {

    val viewModel = viewModel { HomePageViewModel() }

    HomePageScreen(
        navigateToModifyInformationScreen,
        navigateToGamesScreen,
        navigateToGamesResultsScreen,
        navigateToAIDietesScreen,
        navigateToDietesScreen,
        viewModel.color1,
        viewModel.color3,
        viewModel.color5
    )
}

@Composable
fun HomePageScreen(
    navigateToModifyInformationScreen: () -> Unit,
    navigateToGamesScreen: () -> Unit,
    navigateToGamesResultsScreen: () -> Unit,
    navigateToAIDietesScreen: () -> Unit,
    navigateToDietesScreen: () -> Unit,
    color1: Color,
    color3: Color,
    color5: Color,
) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = color5)
            .padding(50.dp, 0.dp, 75.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Spacer(modifier = Modifier.height(25.dp))
            Image(
                painter = painterResource(Res.drawable.Logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.size(25.dp))
            Text("Títol: Dietes i Tests", fontSize = 7.em, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.size(50.dp))
            Text("Afegir, modificar i consultar informació: edat, pes...")
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = { navigateToModifyInformationScreen },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color3,
                    contentColor = color1
                )
            ) {
                Text("Modificar informació")
            }

            Spacer(modifier = Modifier.size(50.dp))
            Text("Fer jocs per a calificar el teus reflexos, la vista...")
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = { navigateToGamesScreen },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color3,
                    contentColor = color1
                )
            ) {
                Text("Fer tests")
            }

            Spacer(modifier = Modifier.size(50.dp))
            Text("Veure els resultats de els tests")
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = { navigateToGamesResultsScreen },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color3,
                    contentColor = color1
                )
            ) {
                Text("Veure els resultats")
            }

            Spacer(modifier = Modifier.size(50.dp))
            Text("Recomenacions, generades per IA, de dieta basades en la informació proporcionada i els resultats de els tests")
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = { navigateToAIDietesScreen },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color3,
                    contentColor = color1
                )
            ) {
                Text("Veure recomenacions de l'IA")
            }

            Spacer(modifier = Modifier.size(50.dp))
            Text("Recomenacions de dietes donades per metges")
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = { navigateToDietesScreen },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color3,
                    contentColor = color1
                )
            ) {
                Text("Veure dietes de metges")
            }

            Spacer(modifier = Modifier.size(75.dp))
        }
    }
}