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
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
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
    val users = mutableStateOf<List<UserData>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            users.value = DietaApi.listUsers()
        }
    }
    fun updateUser(updated: UserData){
        val index = users.value!!.indexOfFirst { it.id == updated.id }
        if (index != -1){
            users.value?.toMutableList()[index] = updated
        }
    }
    fun getUserById(id: String): UserData? =
        users.value?.find { it.id == id }

    fun getUserByEmail(email: String): UserData? =
        users.value?.find { it.email == email }
}
fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun isDecimal(toCheck: String): Boolean {
    val regex = "[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}
object DietaApi {
    val url = "https://apidiet-h6hwe7bffwgwh7gb.northeurope-01.azurewebsites.net/api/authentication"
    val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun listUsers() = client.get(url).body<List<UserData>>()
}