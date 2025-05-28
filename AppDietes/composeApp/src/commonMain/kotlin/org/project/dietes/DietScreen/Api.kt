package org.project.dietes.DietScreen
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.typeInfo
import kotlinx.serialization.json.Json

object MyApi {
    private const val URL = "https://apidiets-axhbbgcubzfjhfda.northeurope-01.azurewebsites.net/api/"

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun listDiet(): List<Diet> =
        client.get(URL + "diet").body(typeInfo<List<Diet>>())

    suspend fun listRecipes(): List<Recipe> =
        client.get(URL + "recipe").body(typeInfo<List<Recipe>>())

}