package org.project.dietes.Token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okio.ByteString.Companion.decodeBase64

@Serializable
data class JwtPayload(
    @SerialName("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier")
    val userId: String,

    @SerialName("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name")
    val name: String? = null,

    val role: String? = null,
    val exp: Long? = null,
    val iss: String? = null,
    val aud: String? = null
)

fun decodeBase64Url(encoded: String): ByteArray? {
    var fixed = encoded.replace('-', '+').replace('_', '/')
    while (fixed.length % 4 != 0) {
        fixed += "="
    }
    return try {
        fixed.decodeBase64()?.toByteArray()
    } catch (e: Exception) {
        println("Error en decodeBase64Url: ${e.message}")
        null
    }
}

fun getUserIdFromToken(token: String): String? {
    return try {
        val parts = token.split(".")
        if (parts.size != 3) {
            println("Token inválido, no tiene 3 partes")
            return null
        }

        val payloadBytes = decodeBase64Url(parts[1])
        if (payloadBytes == null) {
            println("Falló la decodificación Base64 del payload")
            return null
        }

        val json = payloadBytes.decodeToString()
        println("Payload JSON: $json")

        val payload = Json {
            ignoreUnknownKeys = true
        }.decodeFromString<JwtPayload>(json)

        println("Payload decodificado: $payload")

        payload.userId
    } catch (e: Exception) {
        println("Error al decodificar token: ${e.message}")
        e.printStackTrace()
        null
    }
}
