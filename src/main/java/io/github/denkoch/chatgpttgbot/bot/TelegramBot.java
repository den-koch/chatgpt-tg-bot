package io.github.denkoch.chatgpttgbot.bot;

import io.github.denkoch.chatgpttgbot.service.Assistant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;

    @Autowired
    private Assistant assistant;

    public TelegramBot(String botUsername, String botToken){
        super(botToken);
        this.botUsername = botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            Message message = update.getMessage();
            Long chatId = message.getChatId();

            log.info("Message: {}", message);
            String messageText = message.getText();

            String response = assistant.chat(Math.toIntExact(chatId), messageText);

            try {
                execute(new SendMessage(chatId.toString(),response));
            } catch (TelegramApiException e) {
                log.error("Exception during processing telegram api: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
