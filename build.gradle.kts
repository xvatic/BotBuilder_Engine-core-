// High level project gradle file,
// do not place here your dependencies !!!

plugins {
    application
    id("java")
    kotlin("jvm") version "1.6.20"
}

group = "cz.cvut.fit.sp"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("api/src/main/java/cz/cvut/fit/sp/botbuilder/BotBuilderApplication.java")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "api/src/main/java/cz/cvut/fit/sp/botbuilder/BotBuilderApplication.java"
    }
}

// Will update toolchain for Java compile tasks as well
kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17)) // "8"
    }
}

tasks.test {
    useJUnitPlatform()
}
