package telegram.bot.bot;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.config.TelegramBotConfig;
import telegram.bot.factory.MessageSenderFactory;
import telegram.bot.services.MessageSender;

@Slf4j
public class TelegramBot extends TelegramWebhookBot {

    private final TelegramBotConfig telegramBotConfig;

    public TelegramBot(TelegramBotConfig telegramBotConfig) {
        this.telegramBotConfig = telegramBotConfig;
    }

    public String getBotUsername() {
        return telegramBotConfig.getUserName();
    }

    public String getBotToken() {
        return telegramBotConfig.getBotToken();
    }

    public String getBotPath() {
        return telegramBotConfig.getWebHookPath();
    }

    public BotApiMethod<?> onWebhookUpdateReceived(@NonNull Update update) {
        MessageSender sender = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            getInfoAboutUpdate(update);
            sender = new MessageSenderFactory().getMessageSenderForCommand(update);
        }
        assert sender != null;
        return sender.answerForUser(update);
    }

    private void getInfoAboutUpdate(@NonNull Update update) {
        log.info("Do onUpdateReceived from user {} the text message: {}",
                update.getMessage().getFrom().getFirstName(),
                update.getMessage().getText());
    }

}
