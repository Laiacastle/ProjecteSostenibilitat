package org.project.dietes.RecipesScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.project.dietes.DietScreen.Ingredient
import org.project.dietes.DietScreen.MyApi
import org.project.dietes.DietScreen.Recipe

class RecipesVM(id:Int): ViewModel() {
    val dietId = id
    val recipesList = mutableStateOf<List<Recipe>>(listOf())
    val ingredientList = mutableStateOf<List<Ingredient>>(listOf())
    fun refreshRecipes(){
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val result = MyApi.listRecipes(dietId)
                recipesList.value = result
            } catch (e: Exception) {
                recipesList.value = listOf()
                e.printStackTrace()
            }
        }
    }
    fun getIngredients(id: Int){

        viewModelScope.launch(Dispatchers.Default) {
            try {
                ingredientList.value = MyApi.listIngredients(id)
            }catch(e: Exception) {
                ingredientList.value = listOf()
                e.printStackTrace()
            }

        }

    }

    init {
        refreshRecipes()
        println(recipesList.value)
    }
}