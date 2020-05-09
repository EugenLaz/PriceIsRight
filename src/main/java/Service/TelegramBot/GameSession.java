package Service.TelegramBot;

import Service.productProviders.LocalizedPriceProductProvider;
import Service.productProviders.ProductProvider;
import com.mysql.cj.util.StringUtils;
import entity.Product;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GameSession {
    private HashMap<String, Double> guesses = new HashMap<>();
    private HashMap<String, Integer> score = new HashMap<>();

    private Product currentProduct;
    private boolean gameIsGoing = false;
    private ProductProvider productProvider;

    public boolean isGameIsGoing() {
        return gameIsGoing;
    }

    SendMessage ProcessMessage(Update update) {
        String messageText = update.getMessage().getText();
        SendMessage result = null;
        if(productProvider == null){
            result = setProductProvider(update);
        }
        else if (messageText.equals("/finish")) {
            result = finishGame(update);
            guesses.clear();

        } else if (StringUtils.isStrictlyNumeric(messageText.substring(1))) {
            double guess = Double.parseDouble(messageText.substring(1));
            guesses.put(update.getMessage().getFrom().getUserName(), guess);
        }
        return result;
    }

    public ProductProvider getProductProvider() {
        return productProvider;
    }

    public SendMessage setProductProvider(Update update) {
        String text = update.getMessage().getText();
        SendMessage result;
        switch (text) {
            case ("/UA"):
                this.productProvider = new LocalizedPriceProductProvider(Locale.forLanguageTag("UK-ua"));
                result = sendMessage(update, "UA Locale has been set");
                break;
            case ("/US"):
                this.productProvider = new LocalizedPriceProductProvider(Locale.US);
                result = sendMessage(update, "US Locale has been set");
                break;
            default:
                result = sendMessage(update, "Unknown Locale.Choose one of those: \n/UA \n/US");
        }
        return result;
    }

    SendPhoto startGame(Update update) {
        currentProduct = productProvider.getProduct();
        gameIsGoing = true;
        return sendPhoto(update);
    }


    private SendMessage finishGame(Update update) {
        gameIsGoing = false;
        return sendMessage(update,
                findWinner() + "won the game.\n The price is "
                        + String.format("%.2f", currentProduct.getPrice()));
    }

    private String findWinner() {
        System.out.println(guesses.toString());
        double minimalDifference = 999999999;
        String winnerUsername = null;
        double productPrice = currentProduct.getPrice();

        for (Map.Entry<String, Double> entry : guesses.entrySet()) {
            double diff = Math.abs(productPrice - entry.getValue());
            System.out.println(diff);
            if (diff < minimalDifference) {
                minimalDifference = entry.getValue();
                winnerUsername = entry.getKey();
            }
        }
        score.put(winnerUsername, score.getOrDefault(winnerUsername, 0) + 1);
        System.out.println(score.toString());
        return winnerUsername;
    }

    private SendMessage sendMessage(Update update, String text) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        return message;
    }

    private SendPhoto sendPhoto(Update update) {
        long chatId = update.getMessage().getChatId();
        SendPhoto photoMessage = new SendPhoto();
        photoMessage.setChatId(chatId);
        photoMessage.setCaption(currentProduct.getName());
        photoMessage.setPhoto(currentProduct.getImage());
        return photoMessage;
    }


}
