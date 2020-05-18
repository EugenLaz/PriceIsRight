package Service.TelegramBot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private static HashMap<Long, GameSession> sessions = new HashMap<>();
    private Logger logger = LogManager.getLogger(Bot.class);
    private String botToken;

    public Bot() {
        botToken = "1118538657:AAGE07-mLLLXJp2tiHearfg8EbALIsxBXI0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        GameSession session;
        if (!sessions.containsKey(chatId)) {
            sessions.put(chatId, new GameSession());
            logger.info("New session has benn created in " + chatId + " chat");
        }
        session = sessions.get(chatId);
        try {
            executeResponse(session.ProcessMessage(update));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    private void executeResponse(PartialBotApiMethod response) throws TelegramApiException {
        if (response instanceof SendMessage) {
            execute((SendMessage) response);
        } else if (response instanceof SendPhoto) {
            execute((SendPhoto) response);
        }
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


}


