package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.MenuCommands;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.handler.KeyboardHelper;
import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Slf4j
@Component
public class InfoCommandHandler extends RequestHandler {

    private final MenuCommands command = MenuCommands.INFO;

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    public InfoCommandHandler(TelegramService telegramService,
                              KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public void handle(UserRequest userRequest) {

        Long chatId = userRequest.getChatId();

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardHelper.buildInfoMenu();

        telegramService.sendMessage(chatId, Texts.INFO_MESSAGE, inlineKeyboardMarkup);
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
