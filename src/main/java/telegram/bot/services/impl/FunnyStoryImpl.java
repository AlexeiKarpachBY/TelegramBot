package telegram.bot.services.impl;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.services.MessageSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
@Component
public class FunnyStoryImpl implements MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(FunnyStoryImpl.class);

    @Override
    public SendMessage answerForUser(Update update) {
        Message message = update.getMessage();
        return SendMessage.builder().chatId(message.getChatId().toString()).text(getRandomFunnyStory()).build();
    }

    private String getRandomFunnyStory() {
        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL("http://rzhunemogu.ru/RandJSON.aspx?CType=1");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(),"windows-1251"));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new JSONObject(response.toString()).getString("content");
    }

}
