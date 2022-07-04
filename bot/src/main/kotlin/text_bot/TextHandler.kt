package text_bot

import base_bot.BaseTextHandler
import base_bot.UserRequest
import base_bot.model.BotState
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

class TextHandler(private val state: BotState) : BaseTextHandler() {
    override fun executeRequest(message: Message): SendMessage? {
        val request = state.commands[UserRequest(message.text)]

        return request?.let { produceAnswer(it, message.chatId.toString()) }
    }

}