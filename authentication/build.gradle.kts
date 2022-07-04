import libs.Hibernate.hibernate
import libs.JUnit.junit
import libs.Lombok.lombok
import libs.ModelMapper.modelMapper
import libs.MongoDb.mongo
import libs.Spring.spring

plugins {
    java
    id("org.springframework.boot") version ("2.6.3")
    id("io.spring.dependency-management") version ("1.0.11.RELEASE")
    kotlin("jvm") version "1.6.20"
}

repositories {
    mavenCentral()
}

val lombokVersion = "1.18.22"

dependencies {

    implementation(project(":base"))

    modelMapper()
    spring()
    mongo()
    hibernate()
    lombok()
    junit()

    // Have to leave it here, its not working through above methods :(
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

springBoot {
    mainClass.set("api/src/main/java/cz/cvut/fit/sp/botbuilder/BotBuilderApplication.java");
}

tasks.test {
    useJUnitPlatform()
}