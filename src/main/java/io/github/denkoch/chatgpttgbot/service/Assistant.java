package io.github.denkoch.chatgpttgbot.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {

    @SystemMessage("""
                    You are a helpful assistant. Try to respond in a fair and warm manner.
                    If you don't know answer, just tell it.
            """)
    String chat(@MemoryId Integer memoryId, @UserMessage String userMessage);

}
