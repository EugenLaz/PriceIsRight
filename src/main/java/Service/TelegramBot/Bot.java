package Service.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {

    private static HashMap<Long, GameSession> sessions = new HashMap<>();
    private String botToken = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private String botName = "";

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        GameSession session;
        if (!sessions.containsKey(chatId)) {
            sessions.put(chatId, new GameSession());
        }
        session = sessions.get(chatId);
        if (session.isGameIsGoing() || session.getProductProvider() == null) {
            try {
                execute(session.ProcessMessage(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().getText().equals("/score")) {
            try {
                execute(session.getScore(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().getText().equals("/start")) {
            try {
                execute(session.startGame(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}


