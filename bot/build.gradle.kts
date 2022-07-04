import libs.Jaicf.jaicf
import libs.Log4j.log4j
import libs.Telegram.telegram

plugins {
    kotlin("jvm") version "1.6.20"
    java
}

repositories {
    mavenCentral()
    jcenter()
    maven(uri("https://jitpack.io"))
}

dependencies {
    implementation(project(":base"))

    telegram()
    log4j()
    jaicf()
}
