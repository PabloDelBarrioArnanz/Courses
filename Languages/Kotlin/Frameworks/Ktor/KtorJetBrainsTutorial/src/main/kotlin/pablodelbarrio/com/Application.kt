package pablodelbarrio.com

import io.ktor.server.resources.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import pablodelbarrio.com.plugins.configureRouting

/*
    This main contains the initial function for the application
    The server runner function allow a type or server (Netty), port, host and module parameters to be ran
 */
fun main() {

    /*
        Installing plugin in the same embeddedServer function
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            install(CORS)
        }.start(wait = true)
    */

    /*
        Configuring an engine
        embeddedServer function allows us to configure many parameters using the configure option

        embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module, configure = {
            connectionGroupSize = 2
            workerGroupSize = 5
            callGroupSize = 10
            shutdownGracePeriod = 2000
            shutdownTimeout = 3000
            configureBootstrap = {
                // Netty specific config
            }
        }).start(wait = true)
    */

    /*
        Running the server with custom config
        val env = applicationEngineEnvironment {
            envConfig()
        }
        embeddedServer(Netty, env).start(true)
    */

    // Zero port will be random
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


// Every config function are extensions from Application that way we have access to the context
// Here we are listing every plugin config for our project and this function inside also are extension from application
fun Application.module() {
    configureRouting()
    install(CORS)
    install(Resources)
}

// We can have different modules and load or not in init phase

/*
    Plugin are configured during the initialization phase of the server using the 'install' function (takes a plugin as parameter)
    Also can be installed on the embeddedServer function, but it's better to define a module like upper
    As Ktor provides local plugins installation also you can install a plugin for a specific route (overrides global config)
*/

/*
    The configuration parameters also can be defined in an application.conf/yaml file
    ktor {
        application {
            modules = [ com.example.ApplicationKt.module ]
        }
        deployment {
            port = 8080 // Allows env variables ${PORT}
            connectionGroupSize = 2
            workerGroupSize = 5
            callGroupSize = 10
            shutdownGracePeriod = 2000
            shutdownTimeout = 3000
            // specific netty config
            maxInitialLineLength = 2048
            maxHeaderSize = 1024
            maxChunkSize = 42
        }
        security {
            ssl {
                keyStore = keystore.jks
                keyAlias = sampleAlias
                keyStorePassword = foobar
                privateKeyPassword = foobar
            }
        }
    }
    To run an app reading application config we have to use
    Ktor config will be automatically apply and custom one can be read manually
    fun main(args: Array<String>): Unit = EngineMain.main(args)

    // Reading a property from a config file
    val port = environment.config.propertyOrNull("ktor.deployment.port")?.getString() ?: "8080"
    or read
    val env = applicationEngineEnvironment {
        envConfig()
    }
    embeddedServer(Netty, env).start(true)
*/