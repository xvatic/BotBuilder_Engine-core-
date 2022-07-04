package base_bot

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot

interface BaseBotFactory<T : TelegramLongPollingCommandBot?> {
    fun getBot(type: BotType, botName: String, token: String): T?
}