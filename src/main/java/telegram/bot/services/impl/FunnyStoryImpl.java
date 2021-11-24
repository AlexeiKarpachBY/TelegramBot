package telegram.bot.services.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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
@Slf4j
@Component
public class FunnyStoryImpl implements MessageSender {

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
                    new InputStreamReader(con.getInputStream(), "windows-1251"));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return new JSONObject(response.toString()).getString("content");
    }

}
