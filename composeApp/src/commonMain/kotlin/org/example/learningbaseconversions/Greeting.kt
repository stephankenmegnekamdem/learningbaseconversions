package org.example.learningbaseconversions

class Greeting {
    fun greet(): String {
        val platformName = getPlatform().name
        return "Hi there from $platformName!"
    }
}
