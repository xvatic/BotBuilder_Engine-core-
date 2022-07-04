package linking

import base_bot.BaseTelegramBot
import base_bot.model.BotState
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class TelegramLinkingTextBot(
    scenario: ArrayList<BotState>,
    botName: String,
    token: String
) : BaseTelegramBot(botName, token) {

    private val textLinkingHandler = TextLinkingHandler(scenario)

    override fun processNonCommandUpdate(update: Update) {
        val message = update.message
        val answer = textLinkingHandler.executeRequest(message)
        try {
            answer?.let { execute(answer) }
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}