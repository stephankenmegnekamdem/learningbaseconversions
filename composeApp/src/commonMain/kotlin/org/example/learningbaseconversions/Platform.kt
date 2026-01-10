package org.example.learningbaseconversions

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform