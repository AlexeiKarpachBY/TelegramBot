package cosmic.amazing.telegram.BOT.service.impl;

import cosmic.amazing.telegram.BOT.model.MemeUrl;
import cosmic.amazing.telegram.BOT.service.MessageSender;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
public class RandomMeme implements MessageSender {

    @Value("${randomMemeUrl}")
    private String randomMemeUrl;

    @Override
    public SendMessage answerForUser(Update update) {
        Message message = update.getMessage();
        return SendMessage.builder().chatId(message.getChatId().toString()).text(getRandomMeme()).build();
    }

    private String getRandomMeme() {
        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL("https://meme-api.herokuapp.com/gimme");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(response.toString()).getString("url");
    }

}
