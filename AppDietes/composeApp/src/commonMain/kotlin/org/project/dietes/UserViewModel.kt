
package org.project.dietes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
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
import org.project.dietes.DietScreen.MyApi
import org.project.dietes.DietScreen.UserManager
import kotlin.String


@Serializable
data class UserData(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val userName: String,
    val weight: Double,
    val exercise: String,
    val hoursSleep: Double,
    val age: Int,
    val diet: String)
class UsersDataViewModel : ViewModel(){
    var users2 = mutableStateListOf<UserData>()
    var users by mutableStateOf<List<UserData>?>(null)
    val hasTriedLogin = mutableStateOf(false)
    val loginSucces = mutableStateOf(false)
    val hasTriedRegister = mutableStateOf(false)
    val registerSucces = mutableStateOf(false)
    val loading = mutableStateOf(false)
    init {
        viewModelScope.launch(Dispatchers.Default){
            users = MyApi.listUsers()
        }
    }

    fun updateUser(updated: UserData){
        val index = users!!.indexOfFirst { it.id == updated.id }
        if (index != -1){
            users!!.toMutableList()[index] = updated
        }
    }
    fun getUserById(id: String): UserData? =
        users?.find { it.id == id }


    fun getUserByEmail(email: String): UserData? =
        users?.find { it.email == email }

    fun login(email: String, password: String){
        hasTriedLogin.value = true
        viewModelScope.launch(Dispatchers.Default) {
            loading.value = true
            try {

                MyApi.login(email, password)
                println(UserManager.getToken())
                loginSucces.value = true
            }
            finally {
                loading.value = false
            }
        }

    }

    fun register(user: UserData){
        hasTriedRegister.value = true
        viewModelScope.launch(Dispatchers.Default) {
            loading.value = true
            try {

                MyApi.register(user)
                println(UserManager.getToken())
                registerSucces.value = true
            }
            finally {
                loading.value = false
            }
        }
    }
}

fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun isDecimal(toCheck: String): Boolean {
    val regex = "[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}