package org.project.dietes

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

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
    val users = mutableStateListOf<UserData>()

    fun updateUser(updated: UserData){
        val index = users.indexOfFirst { it.idUser == updated.idUser }
        if (index != -1){
            users[index] = updated
        }
    }
    fun removeUser(user: UserData){
        users.remove(user)
    }
    fun getUserById(id: Int): UserData? =
        users.find { it.idUser == id }

    fun getUserByEmail(email: String): UserData? =
        users.find { it.email == email }
}
fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun isDecimal(toCheck: String): Boolean {
    val regex = "[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}