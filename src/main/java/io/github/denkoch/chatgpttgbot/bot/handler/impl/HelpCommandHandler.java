package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.MenuCommands;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.service.TelegramService;
import org.springframework.stereotype.Component;

@Component
public class HelpCommandHandler extends RequestHandler {

    private final MenuCommands command = MenuCommands.HELP;

    private final TelegramService telegramService;

    public HelpCommandHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public void handle(UserRequest userRequest) {
        Long chatId = userRequest.getChatId();
        telegramService.sendMessage(chatId, Texts.HELP_MESSAGE);
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
