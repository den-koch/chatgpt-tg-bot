package io.github.denkoch.chatgpttgbot.service;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import io.github.denkoch.chatgpttgbot.dto.ChatRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class GenAIServiceImpl implements GenAIService{

    private ChatLanguageModel chatLanguageModel;
    private Assistant assistant;

//    @Override
//    public String getResponse(ChatRequest request) {
//
//        List<ChatMessage> messages = new ArrayList<>();
////        messages.add(SystemMessage.systemMessage("Response in french"));
//        messages.add(UserMessage.userMessage(request.message()));
//
//        return chatLanguageModel.generate(messages).content().text();
//    }

    @Override
    public String getResponse(ChatRequest request) {
        return assistant.chat(request.userId(), request.message());
    }
}
