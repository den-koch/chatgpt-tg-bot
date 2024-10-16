package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.MenuCommands;
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
public class ChatGptCommandHandler extends RequestHandler {

    private final MenuCommands command = MenuCommands.CHATGPT;

    private final TelegramService telegramService;
    private final UserSessionService userSessionService;
    private final KeyboardHelper keyboardHelper;
    private final Assistant assistant;

    public ChatGptCommandHandler(TelegramService telegramService,
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

        String response = assistant.chat(Math.toIntExact(chatId),
                Texts.CHATGPT_GREETING_PROMPT + message.getFrom().getFirstName() +
                        "+ add <i> html tag to user name");

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardHelper.buildCancelMenu("cancel_callback");

        telegramService.sendMessage(chatId, response, inlineKeyboardMarkup);

        UserSession userSession = userRequest.getUserSession();
        userSession.setState(ConversationState.WAITING_FOR_TEXT);
        userSessionService.saveSession(chatId, userSession);

    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command.getCommand());
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
