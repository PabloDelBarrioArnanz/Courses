
val slf4jVersion: String by project
val logbackVersion: String by project
val kotlinCoroutinesVersion: String by project
val arrowCoreVersion: String by project

plugins {
    kotlin("jvm") version "1.9.10"
}

group = "com.RockTheJVM"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("io.arrow-kt:arrow-core:$arrowCoreVersion")
}
