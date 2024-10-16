package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.MenuCommands;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartCommandHandler extends RequestHandler {

    private final MenuCommands command = MenuCommands.START;

    private final TelegramService telegramService;

    public StartCommandHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public void handle(UserRequest userRequest) {
        Long chatId = userRequest.getChatId();
        telegramService.sendMessage(chatId, Texts.START_MESSAGE);
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
