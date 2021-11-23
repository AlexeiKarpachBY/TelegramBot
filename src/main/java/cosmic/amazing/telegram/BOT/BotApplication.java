package cosmic.amazing.telegram.BOT;

import cosmic.amazing.telegram.BOT.BotConfig.SpringConfig;
import cosmic.amazing.telegram.BOT.model.CosmicAmazingBOT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class BotApplication {


    public static void main(String[] args) throws TelegramApiException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        CosmicAmazingBOT bot = context.getBean(CosmicAmazingBOT.class);
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
    }

}
