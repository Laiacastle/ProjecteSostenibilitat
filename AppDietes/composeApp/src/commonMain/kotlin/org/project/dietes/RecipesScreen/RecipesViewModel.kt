package org.project.dietes.RecipesScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.project.dietes.DietScreen.MyApi
import org.project.dietes.DietScreen.Recipe

class RecipesVM(id:Int): ViewModel() {
    val dietId = id
    val recipesList = mutableStateOf<List<Recipe>>(listOf())

    fun refreshRecipes(){
        viewModelScope.launch(Dispatchers.Default) {
            recipesList.value = MyApi.listRecipes(dietId)
        }
    }
}