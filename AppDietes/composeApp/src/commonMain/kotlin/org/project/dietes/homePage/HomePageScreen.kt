package org.project.dietes.homePage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomePageScreen(
    navigateToCreateUserScreen: () -> Unit,
    navigateToLogInScreen: () -> Unit,
    navigateToVewStatisticsScreen: () -> Unit,
    navigateToModifyInformationScreen: () -> Unit,
    navigateToGamesScreen: () -> Unit,
    navigateToGamesResultsScreen: () -> Unit,
    navigateToAIDietesScreen: () -> Unit,
    navigateToDietesScreen: () -> Unit
    ) {

    val color1 = Color(142,244,192)
    val color2 = Color(86,165,139)
    val color3 = Color(73,96,94)
    val color4 = Color(180,232,128)
    val color5 = Color(228,213,221)

    HomePageScreen(
        navigateToCreateUserScreen,
        navigateToLogInScreen,
        navigateToVewStatisticsScreen,
        navigateToModifyInformationScreen,
        navigateToGamesScreen,
        navigateToGamesResultsScreen,
        navigateToAIDietesScreen,
        navigateToDietesScreen,
        Color.White,
        color2,
        color5
    )
}

@Composable
fun HomePageScreen(
    navigateToCreateUserScreen: () -> Unit,
    navigateToLogInScreen: () -> Unit,
    navigateToVewStatisticsScreen: () -> Unit,
    navigateToModifyInformationScreen: () -> Unit,
    navigateToGamesScreen: () -> Unit,
    navigateToGamesResultsScreen: () -> Unit,
    navigateToAIDietesScreen: () -> Unit,
    navigateToDietesScreen: () -> Unit,
    color1: Color,
    color3: Color,
    color5: Color,
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(color = color5)
            .padding(50.dp, 0.dp, 75.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Image(
            painter = painterResource(Res.drawable.Logo),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )

        Spacer(modifier = Modifier.size(25.dp))
        Text("Dietes NoSeQue", fontSize = 9.em, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(15.dp))
        Text("El teu cos, el teu joc, la teva dieta perfecta")

        Spacer(modifier = Modifier.size(50.dp))
        Button(
            onClick = { navigateToGamesScreen() },
            modifier = Modifier.width(215.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color3,
                contentColor = color1
            ),
        ) {
            Text("Fer tests")
        }

        Spacer(modifier = Modifier.size(25.dp))
        Button(
            onClick = { navigateToGamesResultsScreen() },
            modifier = Modifier.width(215.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color3,
                contentColor = color1
            )
        ) {
            Text("Resultats")
        }

        Spacer(modifier = Modifier.size(25.dp))
        OutlinedButton(
            onClick = { navigateToAIDietesScreen() },
            modifier = Modifier.width(215.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color3,
                contentColor = color1
            )
        ) {
            Text("Recomenacions amb l'IA")
        }

        Spacer(modifier = Modifier.size(25.dp))
        Button(
            onClick = { navigateToDietesScreen() },
            modifier = Modifier.width(215.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color3,
                contentColor = color1
            )
        ) {
            Text("Dietes asignades")
        }
        Spacer(modifier = Modifier.height(90.dp))
        Text("Hospital NoSeQue, 30/5/2025", fontSize = 2.em)

    }
}
