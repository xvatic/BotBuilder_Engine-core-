package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Hibernate {

    private val dependencies = listOf(
        "org.hibernate.ogm:hibernate-ogm-bom:5.4.1.Final",
        "org.hibernate:hibernate-core:5.6.5.Final",
        "org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final"
    )

    fun DependencyHandler.hibernate(configurationName: String = "implementation") =
        dependencies.forEach { add(configurationName, it) }
}