package org.project.dietes.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomePageScreen(navigateToGamesScreen: () -> Unit, navigateToDietesScreen: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(Res.drawable.Logo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
    }

    Column(Modifier.fillMaxWidth()) {
        Text("1")
        Text("2")
    }
}