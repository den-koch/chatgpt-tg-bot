package io.github.denkoch.chatgpttgbot.bot.handler.impl;

import io.github.denkoch.chatgpttgbot.bot.enums.CallbackCommands;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.handler.KeyboardHelper;
import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.service.TelegramService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class ContactsCommandHandler extends RequestHandler {

    private final CallbackCommands command = CallbackCommands.CONTACTS;

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    public ContactsCommandHandler(TelegramService telegramService,
                                        KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }


    @Override
    public void handle(UserRequest userRequest) {

        CallbackQuery callbackQuery = userRequest.getUpdate().getCallbackQuery();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        Long chatId = callbackQuery.getMessage().getChatId();

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardHelper.buildCancelMenu("cancel_info_callback");

        EditMessageText message = new EditMessageText();
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText(Texts.CONTACTS);

        telegramService.editMessage(message, inlineKeyboardMarkup);
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
