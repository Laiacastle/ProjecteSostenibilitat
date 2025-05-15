package org.project.dietes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform