package org.project.dietes.DietScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

val green = Color(86,165,139)
val darkPink = Color(112, 65, 61)
val background = Color(228,213,221)
val white = Color.White
@Composable
fun DietSreen(navigateToRecipeScreen: () -> Unit){
    val VM = viewModel{DietVM()}
    DietScren(VM.listDiets.value, VM::addDiet, VM::refreshDietList, navigateToRecipeScreen)
}
@Composable
fun DietScren(dietlist: List<Diet>, newDiet: ()-> Unit, refresh: () -> Unit, navigateToScreenRecipe: () -> Unit){
    if (dietlist.size == 0){
        Text("Encara no tens dietes o no t'has loguejat!")
        refresh()
    }
    else{


            Column(
                modifier = Modifier.fillMaxSize().background(background), horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(modifier = Modifier.padding(20.dp).padding(top = 30.dp).align(Alignment.CenterHorizontally)){
                    Image(
                        painter = painterResource(Res.drawable.Logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text("les Meves Dietes", fontWeight = FontWeight.Bold, fontSize = 8.em)
                }
                Box( modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)){
                    LazyColumn(modifier = Modifier.
                    fillMaxWidth().padding(10.dp)) {
                        itemsIndexed(dietlist){
                                _, diet ->

                            Card(
                                modifier = Modifier
                                    .background(background)
                                    .clickable(onClick = {TODO()}).fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            )
                            {
                                Column(){
                                    Text(diet.name, fontSize = 7.em, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text("Coses a tenir en compte d'aquesta dieta:", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())

                                    Text(diet.charac.toString(), textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())

                                    OutlinedButton(onClick = {navigateToScreenRecipe()},
                                        modifier = Modifier.align(Alignment.CenterHorizontally),
                                        shape = RoundedCornerShape(20.dp),
                                        colors =  ButtonDefaults.outlinedButtonColors(

                                            containerColor = white,
                                            contentColor = green
                                        ),
                                        border = BorderStroke(2.dp, green)
                                    ){
                                        Text("Veure receptes", color = green, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))

                                }

                            }
                        }
                    }



                }
                Button(onClick = {newDiet()},

                    colors =  ButtonDefaults.buttonColors(
                        containerColor = darkPink,
                        contentColor = white
                    ))
                {
                    Text("Crear una nova dieta", color = white, fontWeight = FontWeight.Bold)
                }
                }


    }
    }
