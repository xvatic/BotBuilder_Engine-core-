package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Jaicf {

    private const val jaicfVersion = "1.2.4"

    private val dependencies = listOf("com.just-ai.jaicf:telegram:$jaicfVersion",
            "com.just-ai.jaicf:core:$jaicfVersion")

    fun DependencyHandler.jaicf(configurationName: String = "implementation") =
            dependencies.forEach { add(configurationName, it) }
}