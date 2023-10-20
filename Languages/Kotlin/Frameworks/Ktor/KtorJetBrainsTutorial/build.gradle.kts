val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.5"
}

group = "pablodelbarrio.com"
version = "0.0.1"

application {
    mainClass.set("pablodelbarrio.com.ApplicationKt")  // entry point for our app,

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}


// Repositories to download dependencies in this case only maven central
repositories {
    mavenCentral()
}


// Project dependencies
// -jvm suffix in dependencies it's bcs kotlin support different platforms and this are specific for JVM
dependencies {
    implementation("io.ktor:ktor-server-core-jvm") // ktor functionality required
    implementation("io.ktor:ktor-server-netty-jvm") // server also required
    implementation("io.ktor:ktor-network-tls-certificates") // SSL requires a dependency

    implementation("io.ktor:ktor-server-resources")
    implementation("io.ktor:ktor-server-cors") // plugin dependency


    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-cors-jvm:2.3.5")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
