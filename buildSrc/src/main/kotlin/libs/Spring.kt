package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Spring {

    private val dependencies = listOf(
        "org.springframework.boot:spring-boot-starter-data-mongodb",
        "org.springframework.boot:spring-boot-starter-data-rest",
        "org.springframework.boot:spring-boot-starter-hateoas",
        "org.springframework.boot:spring-boot-starter-security",
        "org.springframework.boot:spring-boot-starter-web"
    )

    private val dependenciesTest = listOf(
        "org.springframework.boot:spring-boot-starter-test",
        "de.flapdoodle.embed:de.flapdoodle.embed.mongo",
        "org.springframework.security:spring-security-test"
    )

    fun DependencyHandler.spring(configurationName: String = "implementation") =
        dependencies.forEach { add(configurationName, it) }

    fun DependencyHandler.springTest(configurationName: String = "testImplementation") =
        dependenciesTest.forEach { add(configurationName, it) }
}