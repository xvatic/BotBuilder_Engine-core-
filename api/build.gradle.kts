import libs.JUnit.junit
import libs.Log4j.log4j
import libs.Lombok.lombok
import libs.MongoDb.mongo
import libs.Spring.spring
import libs.Spring.springTest
import libs.Telegram.telegram

plugins {
    java
    kotlin("jvm") version "1.6.20"
    id("org.springframework.boot") version ("2.6.3")
    id("io.spring.dependency-management") version ("1.0.11.RELEASE")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }

repositories {
    mavenCentral()
    jcenter()
    maven(uri("https://jitpack.io"))
}

val lombokVersion = "1.18.22"

dependencies {
    implementation(project(":base"))
    implementation(project(":bot"))
    implementation(project(":authentication"))

    spring()
    mongo()
    telegram()
    lombok()
    log4j()
    springTest()
    junit()

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // Have to leave it here, its not working through above methods :(
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.test {
    useJUnitPlatform()
}