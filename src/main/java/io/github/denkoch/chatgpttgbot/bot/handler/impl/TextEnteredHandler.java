package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.ConversationState;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.handler.KeyboardHelper;
import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.model.UserSession;
import io.github.denkoch.chatgpttgbot.bot.service.TelegramService;
import io.github.denkoch.chatgpttgbot.bot.service.UserSessionService;
import io.github.denkoch.chatgpttgbot.service.Assistant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Slf4j
@Component
public class TextEnteredHandler extends RequestHandler {

    private final TelegramService telegramService;
    private final UserSessionService userSessionService;
    private final Assistant assistant;
    private final KeyboardHelper keyboardHelper;

    public TextEnteredHandler(TelegramService telegramService,
                              UserSessionService userSessionService,
                              Assistant assistant,
                              KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
        this.assistant = assistant;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public void handle(UserRequest userRequest) {
        Message message = userRequest.getUpdate().getMessage();
        Long chatId = userRequest.getChatId();
        String messageText = message.getText();

        String response = assistant.chat(Math.toIntExact(chatId), messageText);

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardHelper.buildCancelMenu("cancel_callback");

        telegramService.sendMessage(chatId, response, inlineKeyboardMarkup);
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate()) &&
                userRequest.getUserSession().getState() == ConversationState.WAITING_FOR_TEXT;
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
