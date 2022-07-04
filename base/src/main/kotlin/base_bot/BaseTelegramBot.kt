package base_bot

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.logging.Logger

abstract class BaseTelegramBot(
    private val botName: String,
    private val token: String
) : TelegramLongPollingCommandBot() {
    override fun getBotUsername(): String = botName

    override fun getBotToken(): String = token

    override fun onUpdatesReceived(updates: List<Update>) =
        super.onUpdatesReceived(updates)

    protected fun getUsername(message: Message): String =
        when (val username = message.from.userName) {
            null -> defaultUsername
            else -> username
        }

    companion object {
        val logger = Logger.getLogger(BaseTelegramBot::class.java.toString())
        const val defaultUsername = "Unknown"
        const val initCommand = "/start"
    }
}