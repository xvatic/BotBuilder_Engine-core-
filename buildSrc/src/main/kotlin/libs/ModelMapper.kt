package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object ModelMapper {
    private const val modelMapperVersion = "3.1.0"

    private val dependencies = listOf(
            "org.modelmapper:modelmapper:$modelMapperVersion"
    )

    fun DependencyHandler.modelMapper(configurationName: String = "implementation") =
            dependencies.forEach { add(configurationName, it) }
}