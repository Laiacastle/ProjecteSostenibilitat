package org.project.dietes.RecipesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import appdietes.composeapp.generated.resources.Logo
import appdietes.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.project.dietes.DietScreen.Ingredient
import org.project.dietes.DietScreen.Recipe
import org.project.dietes.DietScreen.background
import org.project.dietes.DietScreen.darkPink
import org.project.dietes.DietScreen.white

@Composable
fun RecipesScreen(id: Int, onCancel: ()-> Unit){
        val VM = viewModel {RecipesVM(id)}
    RecipesScreen(VM.recipesList.value, VM::refreshRecipes, VM.ingredientList.value, VM::getIngredients, onCancel)
}

@Composable
fun RecipesScreen(recipesList: List<Recipe>, refresh: () -> Unit, ingredientsList: List<Ingredient>, getIng: (Int)-> Unit, onCancel: ()-> Unit){



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
                Text("Receptes", fontWeight = FontWeight.Bold, fontSize = 8.em)
            }
            Box( modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
            )
            {
                if (recipesList.size == 0){
                    Text("Hi hagut un error carregant les receptes, siusplau torna-ho a provar més tard", color = darkPink)
                    refresh()
                }
                else{
                LazyColumn(modifier = Modifier.
                fillMaxWidth().padding(10.dp)) {
                    itemsIndexed(recipesList){
                            _, recipe ->
                        Spacer(modifier = Modifier.height(30.dp))
                        Card(
                            modifier = Modifier
                                .background(background)
                                .clickable(onClick = {TODO()}).fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                        {
                            Column(){
                                getIng(recipe.id)
                                Text(recipe.name, fontSize = 4.em, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                                Spacer(modifier = Modifier.height(20.dp))
                                Text("Descripció", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())

                                Text(recipe.description.toString(), textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                                Text("Ingredients:")
                                for(i in ingredientsList){
                                    TextButton(onClick = {}){
                                        Text(i.name.toString())
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))

                            }

                        }
                    }
                }
                }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Button(
                            onClick = { onCancel() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = darkPink,
                                contentColor = white
                            )
                        ) {
                            Text("Enrere", fontWeight = FontWeight.Bold, color = white)
                        }
                    }
                }




            }

        }




