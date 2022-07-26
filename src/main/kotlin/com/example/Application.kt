package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*


fun main() {
    val envVar: String = System.getenv("PORT") ?: "8080"
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureSockets()
        configureMonitoring()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}
