package io.github.denkoch.chatgpttgbot.controller;

import io.github.denkoch.chatgpttgbot.dto.ChatRequest;
import io.github.denkoch.chatgpttgbot.dto.ChatResponse;
import io.github.denkoch.chatgpttgbot.service.GenAIService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chat")
public class GenerativeController {

    private GenAIService genAIService;

    @PostMapping
    public ResponseEntity<ChatResponse> getChatResponse(@RequestBody @Valid ChatRequest request){
        ChatResponse chatResponse = new ChatResponse(genAIService.getResponse(request));
        return ResponseEntity.ok(chatResponse);
    }
}
