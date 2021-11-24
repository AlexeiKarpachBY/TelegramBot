package telegram.bot.factory;

import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.services.MessageSender;
import telegram.bot.services.impl.FunnyStoryImpl;
import telegram.bot.services.impl.RandomMemeImpl;

import java.util.HashMap;
import java.util.Map;


public class MessageSenderFactory {

    private final Map<String, MessageSender> messageSenderMap = new HashMap<>();

    {
        messageSenderMap.put("/funny_story", new FunnyStoryImpl());
        messageSenderMap.put("/random_meme", new RandomMemeImpl());
    }

    public MessageSender getMessageSenderForCommand(Update update) {
        return messageSenderMap.get(update.getMessage().getText());
    }

}
