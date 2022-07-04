import libs.Hibernate.hibernate
import libs.JUnit.junit
import libs.Log4j.log4j
import libs.Lombok.lombok
import libs.MongoDb.mongo
import libs.Telegram.telegram

plugins {
    java
    kotlin("jvm") version "1.6.20"
}

repositories {
    mavenCentral()
    jcenter()
    maven(uri("https://jitpack.io"))
}

dependencies {
    mongo()
    hibernate()
    telegram()
    lombok()
    log4j()
    junit()
}

tasks.test {
    useJUnitPlatform()
}