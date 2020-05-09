package Service.TelegramBot;

import Service.DataBaseManagment.dao.ProductDao;
import Service.productProviders.LocalizedPriceProductProvider;
import Service.productProviders.ProductProvider;
import com.mysql.cj.util.StringUtils;
import entity.Product;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private String botToken = "1118538657:AAGE07-mLLLXJp2tiHearfg8EbALIsxBXI0";
    private String botName = "";

    private ProductProvider productProvider = new LocalizedPriceProductProvider();
    private Product currentProduct;
    private boolean gameIsGoing = false;

    private static HashMap<String, Double> guesses = new HashMap<>();
    private static HashMap<String, Integer> score = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        String messageText = update.getMessage().getText();
        if (!gameIsGoing && !messageText.equals("/start")) {
            sendMessage(update, "game is not going");
        } else if (messageText.equals("/finish")) {
            finishGame(update);
        } else if (StringUtils.isStrictlyNumeric(messageText.substring(1))) {
            double guess = Double.parseDouble(messageText.substring(1));
            guesses.put(update.getMessage().getFrom().getUserName(), guess);
        } else if (messageText.equals("/start") && !gameIsGoing) {
            startGame(update);
        }

    }

    private void finishGame(Update update) {
        sendMessage(update,
                findWinner() + "won the game.\n The price is "
                        + String.format("%.2f",currentProduct.getPrice()));
        gameIsGoing = false;
    }

    private void startGame(Update update) {
        currentProduct = productProvider.getProduct();
        sendPhoto(update);
        gameIsGoing = true;
    }

    private String findWinner() {
        System.out.println(guesses.toString());
        double minimalDifference = 999999999;
        String winnerUsername = null;
        double productPrice = currentProduct.getPrice();
        for (Map.Entry<String, Double> entry : guesses.entrySet()) {
            if (Math.abs(productPrice - entry.getValue()) < minimalDifference) {
                minimalDifference = entry.getValue();
                winnerUsername = entry.getKey();

            }
        }
        score.put(winnerUsername, score.getOrDefault(winnerUsername, 0) + 1);
        System.out.println(score.toString());
        return winnerUsername;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    private void sendMessage(Update update, String text) {
        long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(Update update) {
        long chatId = update.getMessage().getChatId();
        try {
            SendPhoto photoMessage = new SendPhoto();
            photoMessage.setChatId(chatId);
            photoMessage.setCaption(currentProduct.getName());
            photoMessage.setPhoto(currentProduct.getImage());
            execute(photoMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
