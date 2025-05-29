package org.project.dietes.Token


import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.russhwolf.settings.get

object TokenManager {
    private const val TOKEN_KEY = "auth_token"

    private val settings: Settings by lazy {
        Settings() // Usa el default settings
    }

    fun saveToken(token: String) {
        settings[TOKEN_KEY] = token
    }

    fun getToken(): String? {
        return settings[TOKEN_KEY]
    }

    fun clearToken() {
        settings.remove(TOKEN_KEY)
    }
}