package io.github.denkoch.chatgpttgbot.bot;

import io.github.denkoch.chatgpttgbot.bot.enums.MenuCommands;
import io.github.denkoch.chatgpttgbot.bot.enums.Texts;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import io.github.denkoch.chatgpttgbot.bot.model.UserSession;
import io.github.denkoch.chatgpttgbot.bot.service.UserSessionService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;

    @Autowired
    private Dispatcher dispatcher;

    @Autowired
    private UserSessionService userSessionService;

    public TelegramBot(String botUsername, String botToken) {
        super(botToken);
        this.botUsername = botUsername;
    }

    @PostConstruct
    public void registerBotCommands() {
        List<BotCommand> botCommands = Arrays.stream(MenuCommands.values())
                .map(MenuCommands::toBotCommand)
                .collect(Collectors.toList());

        SetMyCommands setMyCommands = new SetMyCommands(botCommands, new BotCommandScopeDefault(), null);

        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error("Exception during menu commands processing: {}", e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        boolean dispatched = false;

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();

            UserSession session = userSessionService.getSession(chatId);

            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
                    .userSession(session)
                    .chatId(chatId)
                    .build();

            dispatched = dispatcher.dispatch(userRequest);

            if (!dispatched) {
                log.warn("Unexpected command from user!!!");
            }

        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            UserSession session = userSessionService.getSession(chatId);

            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
                    .userSession(session)
                    .chatId(chatId)
                    .build();

            dispatched = dispatcher.dispatch(userRequest);

            if (!dispatched) {
                log.warn("Unexpected command from user!!!");
            }
        }

        if (!dispatched) {
            try {
                SendMessage sendMessage = SendMessage
                        .builder()
                        .text(Texts.NOT_ACCEPTED)
                        .chatId(update.getMessage().getChatId())
                        .parseMode(ParseMode.HTML)
                        .build();

                execute(sendMessage);
            } catch (Exception e) {
                log.error("Exception: ", e);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
