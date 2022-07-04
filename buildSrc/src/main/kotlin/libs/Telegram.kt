package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Telegram {
    private const val telegramVersion = "5.7.1"

    private val dependencies = listOf(
        "org.telegram:telegrambots:$telegramVersion",
        "org.telegram:telegrambotsextensions:$telegramVersion"
    )

    fun DependencyHandler.telegram(configurationName: String = "implementation") =
        dependencies.forEach { add(configurationName, it) }
}