package io.github.denkoch.chatgpttgbot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@Value
public record ChatRequest(@NotNull Integer userId, @NotBlank String message) {}
