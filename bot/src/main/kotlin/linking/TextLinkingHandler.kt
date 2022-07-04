package linking

import base_bot.BaseTextHandler
import base_bot.UserRequest
import base_bot.model.BotState
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

class TextLinkingHandler(private val scenario: ArrayList<BotState>) : BaseTextHandler() {

    private val currentConfiguration = mutableMapOf<String, BotState>()

    override fun executeRequest(message: Message): SendMessage? {
        var currentState = currentConfiguration[message.chatId.toString()]

        if (currentState == null) {
            currentConfiguration[message.chatId.toString()] = scenario.first()
            currentState = currentConfiguration[message.chatId.toString()]
        }

        return currentState?.let {
            val request = currentState.commands[UserRequest(message.text)]

            request?.let { answer ->
                val currentIndex = scenario.indexOf(currentState)
                val nextState = when (currentState) {
                    scenario.last() -> scenario.first()
                    else -> scenario[currentIndex + 1]
                }

                currentConfiguration[message.chatId.toString()] = nextState
                produceAnswer(answer, message.chatId.toString())
            }
        }

    }
}