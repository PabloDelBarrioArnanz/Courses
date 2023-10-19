package pablodelbarrio.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import pablodelbarrio.com.plugins.*


/*
    This main contains the initial function for the application
    The server runner function allow a type or server (Netty), port, host and module parameters to be ran
 */
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


// Every config function are extensions from Application that way we have access to the context
// Here we are listing every plugin config for our project and this function inside also are extension from application
fun Application.module() {
    configureRouting()
}
