package com.mouredevbackendapirestwithtktor.routes

import com.mouredevbackendapirestwithtktor.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val users = mutableListOf(
    User(1, "Pablo", 26, "pablo@gmail.com"),
    User(2, "Laura", 33, "laura@gmail.com"),
)

fun Route.userRouting() {
    route("/user") {
        get {
            if (users.isNotEmpty()) {
                call.respond(users)
            } else {
                call.respondText("No hay usuarios", status = HttpStatusCode.NotFound)
            }
        }
        // {id?} user/ entra por aquí
        // {id} user/ no entraría por aquí
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Id no encontrado", status = HttpStatusCode.BadRequest
            )
            val user = users.find { it.id == id.toInt() } ?: return@get call.respondText(
                "Usuario no encontrado", status = HttpStatusCode.NotFound
            )
            call.respond(user)
        }
        post {
            val user = call.receive<User>() // Si no cuadra perfecto el json 400
            users.add(user)
            call.respondText(
                "Usuario guardado", status = HttpStatusCode.Created
            )
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Id no encontrado", status = HttpStatusCode.BadRequest
            )
            if (users.removeIf { it.id == id.toInt() }) {
                call.respondText("Ususario eliminado correctamente", status = HttpStatusCode.NoContent)
            } else {
                call.respondText(
                    "Usuario no encontrado", status = HttpStatusCode.NotFound
                )
            }
        }
    }
}
