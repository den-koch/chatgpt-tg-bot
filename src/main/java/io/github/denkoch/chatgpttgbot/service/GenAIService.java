package io.github.denkoch.chatgpttgbot.service;

import io.github.denkoch.chatgpttgbot.dto.ChatRequest;

public interface GenAIService {
    String getResponse(ChatRequest request);
}
