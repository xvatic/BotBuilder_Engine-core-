package text_bot

import base_bot.BaseTelegramBot
import base_bot.ChatBotTextAnswer
import base_bot.UserRequest
import base_bot.model.BotState
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class TelegramTextBot(
    commands: Map<UserRequest, ChatBotTextAnswer>,
    botName: String,
    token: String
) : BaseTelegramBot(botName, token) {

    private val textBotHandler = TextHandler(BotState(commands))

    override fun processNonCommandUpdate(update: Update) {
        val message = update.message
        val answer = textBotHandler.executeRequest(message)
        try {
            answer?.let { execute(answer) }
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}