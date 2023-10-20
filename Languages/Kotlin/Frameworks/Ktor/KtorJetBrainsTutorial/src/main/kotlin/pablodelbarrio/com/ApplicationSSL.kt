package pablodelbarrio.com

import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.*
import java.io.*


fun main() {

    /*
        There are 2 ways to configure SSL in out Ktor app
        1. Using application.conf
        ktor {
            deployment {
                sslPort = 8443
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
        2. With the embeddedServer
    */
    val keyStoreFile = File("build/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("sampleAlias") {
            password = "foobar"
            domains = listOf("127.0.0.1", "0.0.0.0", "localhost")
        }
    }
    keyStore.saveToFile(keyStoreFile, "123456")

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        sslConnector(
            keyStore = keyStore,
            keyAlias = "sampleAlias",
            keyStorePassword = { "123456".toCharArray() },
            privateKeyPassword = { "foobar".toCharArray() }) {
            port = 8443
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }

    embeddedServer(Netty, environment).start(wait = true)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}
