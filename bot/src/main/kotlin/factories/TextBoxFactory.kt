package text_bot

import base_bot.BaseBotFactory
import base_bot.BotType
import base_bot.ChatBotTextAnswer
import base_bot.UserRequest
import base_bot.model.BotState
import linking.TelegramLinkingTextBot
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot

class TextBotFactory(
    private val userCommands: Map<UserRequest, ChatBotTextAnswer>? = null,
    private val scenario: ArrayList<BotState>? = null
) : BaseBotFactory<TelegramLongPollingCommandBot?> {
    override fun getBot(type: BotType, botName: String, token: String): TelegramLongPollingCommandBot? {
        return when (type) {
            BotType.TEXT_BOT -> userCommands?.let { TelegramTextBot(it, botName, token) }
            BotType.SCENARIO_BOT -> scenario?.let { TelegramLinkingTextBot(it, botName, token) }
            else -> throw IllegalStateException("Such bot type is not acceptable:$type")
        }
    }
}