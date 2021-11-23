package cosmic.amazing.telegram.BOT.model;

import cosmic.amazing.telegram.BOT.service.MessageSender;
import cosmic.amazing.telegram.BOT.util.ExecutorFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
public class CosmicAmazingBOT extends TelegramLongPollingBot {

    @Value("${userName}")
    private String USER_NAME;

    @Value("${token}")
    private String TOKEN;

    @Autowired
    private ExecutorFactory factory;

    @Override
    public String getBotUsername() {
        return USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        MessageSender messageSender = factory.getExecutorByCommand(update);
        execute(messageSender.answerForUser(update));
    }
}
