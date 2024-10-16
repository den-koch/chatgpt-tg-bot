package io.github.denkoch.chatgpttgbot.bot.handler;

import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class RequestHandler {

    public abstract void handle(UserRequest userRequest);

    public abstract boolean isApplicable(UserRequest request);

    public abstract boolean isGlobal();

    public boolean isCommand(Update update, String command) {
        return update.hasMessage() && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }

    public boolean isCallback(Update update, String command) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        return callbackQuery != null && callbackQuery.getData().equals(command);
    }

    public boolean isTextMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
