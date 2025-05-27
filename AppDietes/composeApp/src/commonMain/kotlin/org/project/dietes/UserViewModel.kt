package org.project.dietes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json



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
    val diet: String
)
class UsersDataViewModel : ViewModel(){
    //val users = mutableStateListOf<UserData>()
    var users by mutableStateOf<List<UserData>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            users = DietaApi.listUsers()
        }
    }
    /*fun updateUser(updated: UserData){
        val index = users!!.indexOfFirst { it.id == updated.id }
        if (index != -1){
            users?.toMutableList()[index] = updated
        }
    }
    fun getUserById(id: String): UserData? =
        users?.find { it.id == id }

    fun getUserByEmail(email: String): UserData? =
        users?.find { it.email == email }*/
}
fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun isDecimal(toCheck: String): Boolean {
    val regex = "[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}
object DietaApi {
    val url = "https://api.sampleapis.com/jokes/goodJokes"
    val client = HttpClient(CIO){
        install(HttpTimeout){
            socketTimeoutMillis = 3 * 60 * 1000
            requestTimeoutMillis = 4 * 60 * 1000
            connectTimeoutMillis = 30 * 1000
        }
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
        /*install(Auth){
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "hashedAccessToken",
                        refreshToken = "hashedRefreshToken"
                    )
                }
            }
        }*/
    }
    suspend fun listUsers() = client.get(url).body<List<UserData>>()
}