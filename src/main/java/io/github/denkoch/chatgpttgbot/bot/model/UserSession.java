package io.github.denkoch.chatgpttgbot.bot.model;

import io.github.denkoch.chatgpttgbot.bot.enums.ConversationState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSession {
    private Long chatId;
    private ConversationState state;
}