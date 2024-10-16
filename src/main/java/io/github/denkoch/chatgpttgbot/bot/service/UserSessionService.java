package io.github.denkoch.chatgpttgbot.bot.service;

import io.github.denkoch.chatgpttgbot.bot.model.UserSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserSessionService {
    private final Map<Long, UserSession> userSessionMap = new HashMap<>();

    public UserSession getSession(Long chatId) {
        return userSessionMap.getOrDefault(chatId, UserSession
                .builder()
                .chatId(chatId)
                .build());
    }

    public void saveSession(Long chatId, UserSession session) {
        userSessionMap.put(chatId, session);
    }
}
