import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
    application
    kotlin("plugin.serialization")
}

group = "dev.zieger"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("io.ktor:ktor-server-netty:1.5.2")
    implementation("io.ktor:ktor-client-okhttp:1.5.2")
    implementation("io.ktor:ktor-client-serialization:1.5.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    implementation("org.slf4j:slf4j-log4j12:1.7.32")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ServerKt")
}