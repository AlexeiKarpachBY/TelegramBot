package telegram.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import telegram.bot.bot.TelegramBot;
import telegram.bot.config.TelegramBotConfig;

@RestController
public class WebhookController {

    private final WebhookBot telegramBot;

    @Autowired
    public WebhookController(TelegramBotConfig telegramBotConfig) {
        this.telegramBot = new TelegramBot(telegramBotConfig);
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

}
