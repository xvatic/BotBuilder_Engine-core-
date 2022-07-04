package base_bot

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class ChatBotTextAnswer(val answer: String) {

    fun convertToMessage(chatId: String) =
        SendMessage().apply {
            this.chatId = chatId
            this.text = answer
        }
}