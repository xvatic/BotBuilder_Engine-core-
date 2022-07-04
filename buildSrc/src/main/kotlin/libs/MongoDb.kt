package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object MongoDb {

    private const val mongoVersion = "3.3.1"
    private const val mongoDriverVersion = "4.4.1"

    private val dependencies = listOf(
        "org.springframework.data:spring-data-mongodb:$mongoVersion",
        "org.mongodb:mongodb-driver-sync:$mongoDriverVersion"
    )

    fun DependencyHandler.mongo(configurationName: String = "implementation") =
        dependencies.forEach { add(configurationName, it) }
}