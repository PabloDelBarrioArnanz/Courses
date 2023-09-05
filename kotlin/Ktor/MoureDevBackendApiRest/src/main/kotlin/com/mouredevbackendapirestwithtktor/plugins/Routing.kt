package com.mouredevbackendapirestwithtktor.plugins

import com.mouredevbackendapirestwithtktor.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRouting()
    }
}