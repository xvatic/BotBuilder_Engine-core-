package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Lombok {

    private const val lombokVersion = "1.18.22"

    private val dependencies = listOf("org.projectlombok:lombok:$lombokVersion")

    fun DependencyHandler.lombok(configurationName: String = "implementation") =
            dependencies.forEach { add(configurationName, it) }

}