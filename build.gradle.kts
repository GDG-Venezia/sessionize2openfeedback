plugins {
    kotlin("jvm") version "1.9.20"
    application
    kotlin("plugin.serialization") version "1.9.20"
}

group = "com.prof18"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // add okhttp as dependency
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}