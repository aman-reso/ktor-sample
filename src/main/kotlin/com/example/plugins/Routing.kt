package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.coroutines.coroutineScope
import java.text.DateFormat

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    routing {
        get("/") {
            coroutineScope {
                val requestUrl = call.request.uri
                val param: Parameters = call.request.queryParameters
                val name = param["name"]
                val message = Response()
                if (name == null) {
                    message.errorMessage = "missing parameter 'name'"
                    call.respond(message)
                } else {
                    message.message = name
                    message.errorMessage = null
                    call.respond(message)
                }
            }
        }
        post("/") {

        }
    }
}


data class Response(var message: String? = null, var errorMessage: String? = null)
