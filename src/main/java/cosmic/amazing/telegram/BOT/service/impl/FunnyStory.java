package cosmic.amazing.telegram.BOT.service.impl;

import cosmic.amazing.telegram.BOT.service.MessageSender;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class FunnyStory implements MessageSender {

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
            con.setRequestProperty("accept", "text/html;charset=charset=utf-8");

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
        JSONObject hhhh = new JSONObject(response.toString());
        System.out.println(hhhh + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new JSONObject(response.toString()).getString("content");
    }
}
