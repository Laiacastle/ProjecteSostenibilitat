package org.project.dietes.DietScreen

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class Result (
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: String,
    @SerialName("gameId")
    val gameId: Int,
    @SerialName("dietId")
    val dietId: Int,
    @SerialName("fiResult")
    val fiResult: Int,
    @SerialName("date")
    val date: LocalDate
)
@Serializable
data class Diet (
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("characteristics")
    val charac: String? ,
    @SerialName("userId")
    val userId: String?,
    @SerialName("recipes")
    val recipes: List<Recipe>,
    @SerialName("results")
    val results: List<Result>

)
@Serializable
data class Recipe(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description:String?,
    @SerialName("ingredients")
    val ingredients: List<Ingredient>
)
@Serializable
data class Ingredient(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name : String?,
    @SerialName("eatForms")
    val eatForms: List<String?>,
    @SerialName("fiber")
    val fiber: Double,
    @SerialName("calories")
    val calories: Double,
    @SerialName("vitamins")
    val vitamins: List<Vitamin>
)
@Serializable
data class Vitamin(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)