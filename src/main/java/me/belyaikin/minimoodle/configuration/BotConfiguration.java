package me.belyaikin.minimoodle.configuration;

import me.belyaikin.minimoodle.MiniMoodleApplication;
import me.belyaikin.minimoodle.bot.Bot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfiguration {
    @Bean
    public TelegramBotsApi telegramBotsApi(Bot bot) {
        try {
            var api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);

            return api;
        } catch (TelegramApiException e) {
            MiniMoodleApplication.LOGGER.error("Unable to register bot: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
