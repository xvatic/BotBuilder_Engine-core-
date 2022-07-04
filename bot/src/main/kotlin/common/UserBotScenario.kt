package common

import base_bot.UserRequest
import base_bot.model.ScenarioBotState
import com.justai.jaicf.builder.Scenario

class ScenarioBot(private val scenario: ArrayList<ScenarioBotState>) {
    private val currentConfiguration = mutableMapOf<String, ScenarioBotState>()

    val userBotScenario = Scenario {

        var botLaunched: Boolean = false

        state("launch") {
            activators {
                regex("/start").onlyIf { !botLaunched }
            }
            action {
                reactions.run {
                    botLaunched = true
                    say("Bot activated! Gears are spinning.")
                }
            }
        }

        state("readUserInput") {
            activators {
                catchAll().onlyIf { request.input != "/stop" && request.input != "/start" && botLaunched }
            }
            action {
                var matchFound: Boolean = false

                var currentState = currentConfiguration[request.clientId]

                // user did not communicate
                if (currentState == null) {
                    currentConfiguration[request.clientId] = scenario.first()
                    currentState = currentConfiguration[request.clientId]
                }

                currentState?.let {
                    val query = currentState.commands[UserRequest(request.input)]
                    query?.let { answer ->
                        val nextState = scenario[query.second - 1]
                        matchFound = true
                        currentConfiguration[request.clientId] = nextState
                        reactions.say(answer.first.answer)
                    }
                }

                if (!matchFound) {
                    reactions.say("Invalid input. Please try again.")
                }
            }
        }

        state("stopBot") {
            activators {
                regex("/stop").onlyIf { botLaunched }
            }
            action {
                botLaunched = false
                currentConfiguration[request.clientId] = scenario.first()
                reactions.say("Connection stopped. Type \'/start\' to enable communication")
            }
        }

        fallback {
            reactions.say("Something went wrong. Try again.")
        }
    }
}