package io.github.denkoch.chatgpttgbot.bot.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardHelper {

    public InlineKeyboardMarkup buildInfoMenu() {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> studentKeyboardButton = new ArrayList<>();
        InlineKeyboardButton studentButton = new InlineKeyboardButton();
        studentButton.setText("Student");
        studentButton.setCallbackData("student_callback");
        studentKeyboardButton.add(studentButton);

        List<InlineKeyboardButton> itKeyboardButton = new ArrayList<>();
        InlineKeyboardButton itTechButton = new InlineKeyboardButton();
        itTechButton.setText("IT-technologies");
        itTechButton.setCallbackData("it_technologies_callback");
        itKeyboardButton.add(itTechButton);

        List<InlineKeyboardButton> contactsKeyboardButton = new ArrayList<>();
        InlineKeyboardButton contactsButton = new InlineKeyboardButton();
        contactsButton.setText("Contacts");
        contactsButton.setCallbackData("contacts_callback");
        contactsKeyboardButton.add(contactsButton);

        markupInline.setKeyboard(List.of(studentKeyboardButton, itKeyboardButton, contactsKeyboardButton));

        return markupInline;
    }

    public InlineKeyboardMarkup buildCancelMenu(String callbackData) {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> studentKeyboardButton = new ArrayList<>();
        InlineKeyboardButton studentButton = new InlineKeyboardButton();
        studentButton.setText("Cancel");
        studentButton.setCallbackData(callbackData);
        studentKeyboardButton.add(studentButton);

        markupInline.setKeyboard(List.of(studentKeyboardButton));

        return markupInline;
    }

}
