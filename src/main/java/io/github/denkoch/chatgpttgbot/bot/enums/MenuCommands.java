package io.github.denkoch.chatgpttgbot.bot.enums;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum MenuCommands {
    START("/start", "start bot conversation"),
    CHATGPT("/chatgpt", "send request to ChatGPT"),
    INFO("/info", "get student's info"),
    HELP("/help", "how to use this bot");

    private final String command;
    private final String description;
    private static final Map<String, MenuCommands> lookup = new HashMap<>();

    static {
        for (MenuCommands command : MenuCommands.values()) {
            lookup.put(command.getCommand(), command);
        }
    }

    MenuCommands(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public BotCommand toBotCommand() {
        return new BotCommand(command, description);
    }

    public static MenuCommands get(String command) {
        return lookup.get(command);
    }

}
