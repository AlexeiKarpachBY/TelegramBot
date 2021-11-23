package cosmic.amazing.telegram.BOT.util;

import cosmic.amazing.telegram.BOT.service.impl.FunnyStory;
import cosmic.amazing.telegram.BOT.service.MessageSender;
import cosmic.amazing.telegram.BOT.service.impl.RandomMeme;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ExecutorFactory {

    private final Map<String, MessageSender> executorFactory = new HashMap<>();

    public MessageSender getExecutorByCommand(Update update) {
        String command = getCommandFromUpdate(update);
        return executorFactory.get(command);
    }

    private String getCommandFromUpdate(Update update) {

        Message message = update.getMessage();
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                return message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
            }
        }
        return "";
    }

    @PostConstruct
    public void init() {
        executorFactory.put("/funny_story", new FunnyStory());
        executorFactory.put("/random_meme", new RandomMeme());
    }
}


