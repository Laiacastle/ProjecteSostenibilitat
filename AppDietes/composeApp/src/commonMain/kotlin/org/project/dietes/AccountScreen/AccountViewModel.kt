package org.project.dietes.AccountScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.project.dietes.DietScreen.MyApi
import org.project.dietes.DietScreen.UserManager
import org.project.dietes.Token.TokenManager
import org.project.dietes.Token.getUserIdFromToken
import org.project.dietes.User.UserData

class AccountViewModel : ViewModel(){
    val user = mutableStateOf<UserData?>(null)
    val token = TokenManager.getToken()
    val userId = token?.let { getUserIdFromToken(it) }

    fun logout(navigate: ()-> Unit){
        if(token!= null){
            TokenManager.clearToken()
            UserManager.clearToken()
            viewModelScope.launch {
            delay(1000)
            navigate()
            }
        }
    }
    fun getUser(){
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
     init {
         getUser()
     }
}