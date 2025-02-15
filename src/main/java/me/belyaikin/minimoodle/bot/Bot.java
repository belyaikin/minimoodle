package me.belyaikin.minimoodle.bot;

import me.belyaikin.minimoodle.MiniMoodleApplication;
import me.belyaikin.minimoodle.model.User;
import me.belyaikin.minimoodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    private UserService userService;

    public Bot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        String message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        long userId = update.getMessage().getFrom().getId();

        if (!userService.isUserRegistered(update.getMessage().getFrom().getId())) {
            // TODO: make better check
            if (message.length() < 32) {
                sendMessage(chatId,
                        "Please send me a valid Moodle security key."
                );
                return;
            }
            User user = new User();
            user.setTelegramId(userId);
            user.setMoodleKey(message);

            userService.create(user);

            sendMessage(chatId,
                    "Welcome to the MiniMoodle! Feel yourself like at home :)"
            );

            return;
        }

        sendMessage(chatId,
                "You're already registered!"
        );
    }

    @Override
    public String getBotUsername() {
        return "mini_moodle_bot";
    }

    public void sendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            MiniMoodleApplication.LOGGER.error("Error sending message: {}", e.getMessage());
        }
    }
}
