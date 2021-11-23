package telegram.bot.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.services.MessageSender;
import telegram.bot.services.impl.FunnyStoryImpl;
import telegram.bot.services.impl.RandomMemeImpl;

import java.util.HashMap;
import java.util.Map;


public class MessageSenderFactory {

    private static final Logger logger = LoggerFactory.getLogger(MessageSenderFactory.class);

    private final Map<String, MessageSender> messageSenderMap = new HashMap<>();

    {
        messageSenderMap.put("/funny_story", new FunnyStoryImpl());
        messageSenderMap.put("/random_meme", new RandomMemeImpl());
    }

    public MessageSender getMessageSenderByCommand(Update update) {

        logger.info(update.getMessage().getText());
        return messageSenderMap.get(update.getMessage().getText());
    }
}
