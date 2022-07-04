package base_bot

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

abstract class BaseTextHandler {

    /**
     * Identify user response and produce response
     *
     * @param message - sender's request
     */
    abstract fun executeRequest(message: Message): SendMessage?

    /**
     * Produce answer for user command
     */
    fun produceAnswer(
        answer: ChatBotTextAnswer,
        chatId: String
    ): SendMessage {
        return answer.convertToMessage(chatId)
    }
}