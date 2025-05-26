package org.project.dietes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.project.dietes.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            //App()
            //Navigation()
            ListUsers()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}