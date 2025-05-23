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
import kotlinx.serialization.json.Json

data class UserData(
    val idUser: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val weight: Float,
    val exerciseDone: String,
    val sleepTime: Float,
    val age: Int,
    val password: String
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
        val index = users.value!!.indexOfFirst { it.idUser == updated.idUser }
        if (index != -1){
            users.value?.toMutableList()[index] = updated // no va [index]
        }
    }
    fun getUserById(id: Int): UserData? =
        users.value?.find { it.idUser == id }

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
    val url = "https://apidiet-h6hwe7bffwgwh7gb.northeurope-01.azurewebsites.net/"
    val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun listUsers() = client.get(url).body<List<UserData>>()
}