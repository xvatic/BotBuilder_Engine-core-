package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Log4j {

    private const val log4jVersion = "1.2.17"

    private val dependencies = listOf("log4j:log4j:$log4jVersion")

    fun DependencyHandler.log4j(configurationName: String = "implementation") =
        dependencies.forEach { add(configurationName, it) }
}