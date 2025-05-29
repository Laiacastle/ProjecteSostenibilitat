
package org.project.dietes.User

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.project.dietes.DietScreen.MyApi
import org.project.dietes.DietScreen.UserManager
import org.project.dietes.Token.TokenManager
import org.project.dietes.Token.getUserIdFromToken
import kotlin.String


@Serializable
data class UserData(
    val id: String?,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val userName: String,
    val weight: Double,
    val exercise: String,
    val hoursSleep: Double,
    val age: Int,
    val diet: String?)
class UsersVM : ViewModel(){
    val user = mutableStateOf<UserData?>(null)
    val token = TokenManager.getToken()
    val userId = token?.let { getUserIdFromToken(it) }
    var users by mutableStateOf<List<UserData>?>(null)
    val hasTriedLogin = mutableStateOf<Boolean?>(null)
    val loginSucces = mutableStateOf<Boolean?>(null)
    val hasTriedRegister = mutableStateOf<Boolean?>(null)
    val registerSucces = mutableStateOf<Boolean?>(null)
    val updateSucces = mutableStateOf<Boolean?>(null)
    val hasTriedUpdate = mutableStateOf<Boolean?>(null)
    val loading = mutableStateOf(false)
    init {
        viewModelScope.launch(Dispatchers.Default){
            users = MyApi.listUsers()
        }
    }

    fun updateUser(id:String, updated: UserData){
        hasTriedUpdate.value = true
        viewModelScope.launch(Dispatchers.Default) {
            loading.value = true
            try {
                MyApi.updateUser(id, updated)
                println(UserManager.getToken())
                updateSucces.value = true
            }
            catch (e: Exception) {
                updateSucces.value = false
                println("Error updating user: ${e.message}")
            }
            finally {
                loading.value = false

            }
        }
    }
    fun getUserById(id: String): UserData? =
        users?.find { it.id == id }


    fun login(email: String, password: String){
        hasTriedLogin.value = true
        viewModelScope.launch(Dispatchers.Default) {
            loading.value = true
            try {

                MyApi.login(email, password)
                println(UserManager.getToken())
                loginSucces.value = true
            }
            catch (e: Exception) {
                loginSucces.value = false
                println("Error login user: ${e.message}")
            }
            finally {
                loading.value = false

            }
        }

    }

    fun register(user: UserData) {
        hasTriedRegister.value = true
        viewModelScope.launch(Dispatchers.Default) {
            loading.value = true
            try {
                MyApi.register(user)
                println(UserManager.getToken())
                registerSucces.value = true
            } catch (e: Exception) {
                registerSucces.value = false
                println("Error registering user: ${e.message}")
            } finally {
                loading.value = false
            }
        }
    }


    fun getUser() {
        if (token == null) {
            println("Token is null")
            return
        }

        if (userId == null) {
            println("userId is null (malformado)")
            return
        }

        println("Token: $token")
        println("userId: $userId")

        viewModelScope.launch(Dispatchers.Default) {
            try {
                user.value = MyApi.getUser(userId.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cleanVars(){
        if(TokenManager.getToken() == null){
            hasTriedLogin.value = false
            loginSucces.value = false
            hasTriedRegister.value = false
            registerSucces.value = false
            updateSucces.value = false
            hasTriedUpdate.value = false
        }
    }
    init{
        getUser()
        cleanVars()
    }
}

fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun isDecimal(toCheck: String): Boolean {
    val regex = "[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}