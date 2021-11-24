package telegram.bot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TelegramBotConfig {

    @Value("${telegrambot.webHookPath}")
    private String webHookPath;

    @Value("${telegrambot.userName}")
    private String userName;

    @Value("${telegrambot.botToken}")
    private String botToken;

}
