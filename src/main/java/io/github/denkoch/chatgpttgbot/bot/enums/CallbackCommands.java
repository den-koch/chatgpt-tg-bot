package io.github.denkoch.chatgpttgbot.bot.enums;

import lombok.Getter;

@Getter
public enum CallbackCommands {

    STUDENT("student_callback"),
    IT_TECHNOLOGIES("it_technologies_callback"),
    CONTACTS("contacts_callback"),
    CANCEL("cancel_callback"),
    CANCEL_INFO("cancel_info_callback");

    private final String command;

    CallbackCommands(String command) {
        this.command = command;
    }

}
