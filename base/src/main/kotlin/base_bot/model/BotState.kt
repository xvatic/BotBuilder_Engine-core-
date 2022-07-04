package base_bot.model

import base_bot.ChatBotTextAnswer
import base_bot.UserRequest

data class BotState(
        val commands: Map<UserRequest, ChatBotTextAnswer>
)

data class ScenarioBotState(
        val commands: Map<UserRequest, Pair<ChatBotTextAnswer, Int>>
)