package io.github.denkoch.chatgpttgbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Slf4j
@Component
public class BotSender extends DefaultAbsSender {
    private String botToken;

    public BotSender(@Value("${bot.token}") String botToken) {
        super(new DefaultBotOptions(), botToken);
    }
}
