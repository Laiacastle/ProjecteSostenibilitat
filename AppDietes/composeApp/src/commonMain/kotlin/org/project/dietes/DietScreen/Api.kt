package org.project.dietes.DietScreen
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.typeInfo
import kotlinx.serialization.json.Json
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.put
import io.ktor.http.isSuccess
import org.project.dietes.Token.TokenManager
import org.project.dietes.User.UserData


object UserManager {
    private var _token: String? = null
    fun getToken(): String? = _token
    fun saveToken(newToken: String){
        _token = newToken
        TokenManager.saveToken(_token.toString())
    }
    fun clearToken(){
        _token = null
    }


}

object MyApi {
    private const val URL = "https://apidiets-axhbbgcubzfjhfda.northeurope-01.azurewebsites.net/api/"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(DefaultRequest) {
            headers.remove(HttpHeaders.Authorization)
            UserManager.getToken()?.let { token ->
                header(HttpHeaders.Authorization, "Bearer $token")
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 15_000
        }
    }
    suspend fun listDiet(): List<Diet> =
        client.get(URL + "diet").body(typeInfo<List<Diet>>())

    suspend fun listRecipes(id:Int): List<Recipe> =
        client.get(URL + "recipe/diet/$id").body(typeInfo<List<Recipe>>())

    suspend fun listIngredients(id:Int): List<Ingredient> =
        client.get(URL + "ingredient/recipe/$id").body(typeInfo<List<Ingredient>>())

    suspend fun getUser(id: String): UserData =
        client.get(URL+"authentication/$id").body(typeInfo<UserData>())


    suspend fun listUsers(): List<UserData> =
        client.get(URL + "authentication").body(typeInfo<List<UserData>>())

    suspend fun login(email: String, password: String):Boolean {
        val response = client.post(URL + "authentication/login") {
            contentType(ContentType.Application.Json)
            setBody(mapOf("email" to email, "password" to password))
        }
        if (response.status.isSuccess()) {
            val rawToken: String = response.body()
            val token = rawToken.trim('"')
            println("Cleaned token: $token")
            UserManager.saveToken(token)
            return true
        } else {
            val errorMessage: String = response.body()
            println("Login failed: $errorMessage")
            return false
        }
    }

    suspend fun register(user: UserData): Boolean{
        val response = client.post(URL + "authentication/register") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        if (response.status.isSuccess()) {
            println("Register succesful")
            return true
        } else {
            val errorMessage: String = response.body()
            println("Register failed: $errorMessage")
            return false
        }
    }

    suspend fun updateUser(id: String, user: UserData): Boolean{
        val response = client.put(URL + "authentication/$id"){
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        if (response.status.isSuccess()) {
            println("Register succesful")
            return true
        } else {
            val errorMessage: String = response.body()
            println("Register failed: $errorMessage")
            return false
        }
    }
}