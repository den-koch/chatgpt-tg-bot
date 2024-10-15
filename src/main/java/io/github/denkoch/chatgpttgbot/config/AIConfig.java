package io.github.denkoch.chatgpttgbot.config;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import io.github.denkoch.chatgpttgbot.service.Assistant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

//    @Bean
//    public Assistant assistant(){
//        return AiServices.builder(Assistant.class)
//                .chatLanguageModel(chatLanguageModel)
//                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
//                .build();
//    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider(){
        return memoryId -> MessageWindowChatMemory.withMaxMessages(10);
    }

}
