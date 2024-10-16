package io.github.denkoch.chatgpttgbot.bot.enums;

public interface Texts {
    String START_MESSAGE = """
            Welcome to the bot! Select the desired section from the menu to interact further.
                        
            You can control me by sending these commands:
            /start - start bot conversation
            /info - get student's info
            /chatgpt - send request to ChatGPT
            /help - how to use this bot
            """;

    String CHATGPT_GREETING_PROMPT = "Greeting for user. User name = ";
    String INFO_MESSAGE = "Information about the <i>Bot Creator</i>:";
    String HELP_MESSAGE = """
            You can control me by sending these commands:
            /start - start bot conversation
            /info - get student's info
            /chatgpt - send request to ChatGPT
            /help - how to use this bot
            """;

    String STUDENT_INFO_MESSAGE = """
            <b>Faculty</b> : FIOT
            <b>Group</b> : IO-11
            <b>Student</b> : Denys Kochetov
            """;

    String IT_TECHNOLOGIES_INFO_MESSAGE = """
            <b>Technologies</b>:
              - Spring Framework
              - Telegram API
              - Langchain4j
            """;
    String CONTACTS = """
            <b>Contacts:</b>:
              phone number: +380 99 999 9999
              email: qwerty123@gmail.com
            """;

    String CHATGPT_CONVERSATION_END_MESSAGE = "Thank you for chatting with ChatGPT!";

    String NOT_ACCEPTED = "No such command or your request is not accepted!";
}
