package io.github.denkoch.chatgpttgbot.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatResponse(@NotBlank String message) {}