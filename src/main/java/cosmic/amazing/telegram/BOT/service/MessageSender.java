package cosmic.amazing.telegram.BOT.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageSender {

    SendMessage answerForUser(Update update);

}
