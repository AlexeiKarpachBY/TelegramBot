package telegram.bot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.config.TelegramBotConfig;
import telegram.bot.factory.MessageSenderFactory;
import telegram.bot.services.MessageSender;

public class TelegramBot extends TelegramWebhookBot {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final TelegramBotConfig telegramBotConfig;



    public TelegramBot(TelegramBotConfig telegramBotConfig) {
        logger.info("TelegramBot created, telegramBotConfig = {}", telegramBotConfig);
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

    public BotApiMethod onWebhookUpdateReceived(Update update) {
        logger.info("onUpdateReceived");
        MessageSender sender =null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            logger.info("onUpdateReceived from user {} the text message: {}",
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getText());
            sender = new MessageSenderFactory().getMessageSenderByCommand(update);
            logger.info(sender.toString());


        }
        return sender.answerForUser(update);
    }
}
