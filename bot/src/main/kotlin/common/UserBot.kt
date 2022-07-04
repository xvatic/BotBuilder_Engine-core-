package common

import base_bot.model.BotState
import base_bot.model.ScenarioBotState
import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.regex.RegexActivator
import java.util.ArrayList

class CommonBot {
    fun createCommonBot(scenario: ArrayList<ScenarioBotState>) = BotEngine(
            scenario = ScenarioBot(scenario).userBotScenario,
            activators = arrayOf(RegexActivator)
    )
}
