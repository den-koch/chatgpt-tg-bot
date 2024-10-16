package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.CallbackCommands;
import io.github.denkoch.chatgpttgbot.bot.enums.ConversationState;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.model.UserSession;
import io.github.denkoch.chatgpttgbot.bot.service.TelegramService;
import io.github.denkoch.chatgpttgbot.bot.service.UserSessionService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CancelCommandHandler extends RequestHandler {

    private final CallbackCommands command = CallbackCommands.CANCEL;

    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public CancelCommandHandler(TelegramService telegramService,
                                UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }


    @Override
    public void handle(UserRequest userRequest) {

        CallbackQuery callbackQuery = userRequest.getUpdate().getCallbackQuery();
        Long chatId = callbackQuery.getMessage().getChatId();

        telegramService.sendMessage(chatId, Texts.CHATGPT_CONVERSATION_END_MESSAGE);

        UserSession userSession = userRequest.getUserSession();
        userSession.setState(ConversationState.CONVERSATION_ENDED);
        userSessionService.saveSession(chatId, userSession);
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCallback(userRequest.getUpdate(), command.getCommand());
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
