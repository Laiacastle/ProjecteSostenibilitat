package org.project.dietes.DietScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Diet (
    @SerialName("Id")
    val id: Int,
    @SerialName("Characteristics")
    val charac: List<String?> ,
    @SerialName("Name")
    val name: String,
    @SerialName("UserId")
    val userId: String?,
    @SerialName("Recipes")
    val recipes: List<Recipe>


)
@Serializable
data class Recipe(
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String,
    @SerialName("Description")
    val description:String?,
    @SerialName("Ingredients")
    val ingredients: List<Ingredient>
)
@Serializable
data class Ingredient(
    @SerialName("Id")
    val id: Int,
    @SerialName("EatForms")
    val eatForms: List<String?>,
    @SerialName("Fiber")
    val fiber: Double,
    @SerialName("Calories")
    val calories: Double,
    @SerialName ("Vitamins")
    val vitamins: List<Vitamin>
)
@Serializable
data class Vitamin(
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String
)
class DietVM : ViewModel(){
    val listDiets = mutableStateOf(listOf<Diet> (
        Diet(1, listOf<String>("hola"), "Prueba", "prueba",
            listOf<Recipe>(
                Recipe(1, "receta", "lorem ipsum",
                    listOf<Ingredient>(
                        Ingredient(1, listOf<String>("cocido"), 2.2, 2.2,
                            listOf<Vitamin>(
                                Vitamin(1, "A12"),
                                Vitamin(2, "B6")
                            ))
                    ))
            ))))
    fun addDiet(){
        val tokenuserID = null
        if(tokenuserID != null){
            TODO()
        }
    }
    fun refreshDietList(){

    }
    init{
        //api nsq con el user id cogido del token
    }
}