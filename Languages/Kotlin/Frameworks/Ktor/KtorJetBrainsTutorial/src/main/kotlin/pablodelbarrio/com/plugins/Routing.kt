package pablodelbarrio.com.plugins

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Routing it's the default way to handle incoming request in Ktor but also supports type-safe routing

// Routing example
// Every thing inside a route works with a coroutine
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}

@Resource("/person")
class Person() {

    @Resource("new")
    class CreatePerson(val parent: Person = Person())

    @Resource("{id}")
    class Id(val id: Long)

}


// Structuring routes way

// CustomerRoutes.kt
fun Route.customerByIdRoute() {
    get("/customer/{id}") {
        // ...
    }
}
fun Route.createCustomerRoute() {
    post("/customer") {
        // ...
    }
}

// From app routes or package routes
fun Application.customerRoutes() {
    routing {
        // customerRouting()
    }
}

// From main
fun Application.module() {
    // routes()
}