package io.github.denkoch.chatgpttgbot.bot.service;

import io.github.denkoch.chatgpttgbot.bot.BotSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Service
@Slf4j
public class TelegramService {

    private final BotSender botSender;

    public TelegramService(BotSender botSender) {
        this.botSender = botSender;
    }

    public void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    public void sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = SendMessage
                .builder()
                .text(text)
                .chatId(chatId.toString())
                .replyMarkup(replyKeyboard)
                .parseMode(ParseMode.HTML)
                .build();
        execute(sendMessage);
    }

    public void editMessage(EditMessageText editMessageText) {
        editMessage(editMessageText, null);
    }

    public void editMessage(EditMessageText editMessageText, InlineKeyboardMarkup replyKeyboard) {
        editMessageText.setParseMode(ParseMode.HTML);
        editMessageText.setReplyMarkup(replyKeyboard);
        execute(editMessageText);
    }

    public void editMessage(EditMessageReplyMarkup editMessageReplyMarkup) {
        execute(editMessageReplyMarkup);
    }

    private void execute(BotApiMethod botApiMethod) {
        try {
            botSender.execute(botApiMethod);
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
    }
}
