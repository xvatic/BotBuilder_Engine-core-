package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object JUnit {

    private const val jUnitVersion = "5.8.1"

    private val dependenciesRuntime = listOf(
        "org.junit.jupiter:junit-jupiter-engine:$jUnitVersion"
    )

    private val dependenciesTest = listOf(
        "org.junit.jupiter:junit-jupiter-api:$jUnitVersion"
    )

    fun DependencyHandler.junit() {
        this.junitTest()
        this.junitRuntime()
    }

    private fun DependencyHandler.junitRuntime(configurationName: String = "testRuntimeOnly") =
        dependenciesRuntime.forEach { add(configurationName, it) }

    private fun DependencyHandler.junitTest(configurationName: String = "testImplementation") =
        dependenciesTest.forEach { add(configurationName, it) }
}