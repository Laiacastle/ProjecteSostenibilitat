package org.project.dietes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpTimeoutConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlin.String


@Serializable
data class UserData(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val userName: String,
    val weight: Int,
    val exercise: String,
    val hoursSleep: Int,
    val age: Int,
    val diet: String)
@Serializable
data class PostUser(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val userName: String,
    val weight: Int,
    val exercise: String,
    val hoursSleep: Int,
    val age: Int,
    val diet: String
)
class UsersDataViewModel : ViewModel(){
    var users2 = mutableStateListOf<UserData>()
    var users by mutableStateOf<List<UserData>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            users = DietaApi.listUsers()
        }
    }

    /*var users by mutableStateOf<List<UserData>?>(null)
    init {
        users = users2
    }*/
    fun updateUser(updated: UserData){
        val index = users!!.indexOfFirst { it.id == updated.id }
        if (index != -1){
            users?.toMutableList()[index] = updated
        }
    }
    fun getUserById(id: String): UserData? =
        users?.find { it.id == id }

    fun getUserByEmail(email: String): UserData? =
        users?.find { it.email == email }
}
fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun isDecimal(toCheck: String): Boolean {
    val regex = "[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}
object DietaApi {
    val url = "https://apidiets-axhbbgcubzfjhfda.northeurope-01.azurewebsites.net/api/authentication"
    val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }

    }
    suspend fun listUsers() = client.get(url).body<List<UserData>>()
}
/*suspend fun UserPost(){
    val url = "https://apidiets-axhbbgcubzfjhfda.northeurope-01.azurewebsites.net/api/authentication"
    val client = HttpClient()
    val post = PostUser(
        id = "userid1",
        name = "",
        surname = "",
        email = "",
        password = "",
        userName = "",
        weight = 5,
        exercise = "",
        hoursSleep = 6,
        age = 21,
        diet = ""
    )
    val response = client.post(url){
        setBody(post)
        contentType(ContentType.Application.Json)
    }
}*/
/*
@Composable
fun AddUsers(){
    var users = UsersDataViewModel().users
    val users2 = UsersDataViewModel().users2
    users = users?.plus(users2.toMutableStateList())
    UsersDataViewModel().users = users
}*/