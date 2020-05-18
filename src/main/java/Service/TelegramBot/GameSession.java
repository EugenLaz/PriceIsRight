package Service.TelegramBot;

import Service.productProviders.LocalizedPriceProductProvider;
import Service.productProviders.ProductProvider;
import com.mysql.cj.util.StringUtils;
import entity.Product;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class GameSession {
    private HashMap<String, Double> guesses = new HashMap<>();
    private HashMap<String, Integer> score = new HashMap<>();

    private Product currentProduct;
    private boolean gameIsGoing = false;
    private ProductProvider productProvider;

    PartialBotApiMethod ProcessMessage(Update update) {
        String messageText = update.getMessage().getText();
        PartialBotApiMethod result = null;
        if (productProvider == null) {
            result = setProductProvider(update);
        } else if ((!gameIsGoing) && messageText.equals("/start")) {
            result = startGame(update);
        } else if (messageText.equals("/finish")) {
            result = finishGame(update);
            guesses.clear();
        } else if (StringUtils.isStrictlyNumeric(messageText.substring(1))) {
            double guess = Double.parseDouble(messageText.substring(1));
            guesses.put(update.getMessage().getFrom().getUserName(), guess);
        } else if (messageText.equals("/score")) {
            result = getScore(update);
        }
        return result;
    }

    private SendMessage setProductProvider(Update update) {
        String text = update.getMessage().getText();
        SendMessage result;
        switch (text) {
            case ("/UA"):
                this.productProvider = new LocalizedPriceProductProvider(Locale.forLanguageTag("UK-ua"));
                result = generateMessage(update, "UA Locale has been set");
                break;
            case ("/US"):
                this.productProvider = new LocalizedPriceProductProvider(Locale.US);
                result = generateMessage(update, "US Locale has been set");
                break;
            default:
                result = generateMessage(update, "Unknown Locale.Choose one of those: \n/UA \n/US");
        }
        return result;
    }

    private SendPhoto startGame(Update update) {
        currentProduct = productProvider.getProduct();
        gameIsGoing = true;
        return generateMessageWithPhoto(update);
    }

    private SendMessage getScore(Update update) {
        StringBuilder result = new StringBuilder();
        score.forEach((Key, Value) -> result.append(Key).append(":").append(Value).append("\n"));
        return generateMessage(update, result.toString());
    }

    private SendMessage finishGame(Update update) {
        gameIsGoing = false;
        return generateMessage(update, findWinner());
    }

    private String findWinner() {
        if (guesses.size() == 0) {
            return "There were no bets";
        }
        String winnerUsername = null;

        double minimalDifference = 999999999;
        double productPrice = currentProduct.getPrice();

        for (Map.Entry<String, Double> entry : guesses.entrySet()) {
            double diff = Math.abs(productPrice - entry.getValue());
            if (diff < minimalDifference) {
                minimalDifference = entry.getValue();
                winnerUsername = entry.getKey();
            }
        }
        score.put(winnerUsername, score.getOrDefault(winnerUsername, 0) + 1);
        return findWinner() + "won the game.\n The price is "
                + String.format("%.2f", currentProduct.getPrice());
    }

    private SendMessage generateMessage(Update update, String text) {
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        return message;
    }

    private SendPhoto generateMessageWithPhoto(Update update) {
        long chatId = update.getMessage().getChatId();
        SendPhoto photoMessage = new SendPhoto();
        photoMessage.setChatId(chatId);
        photoMessage.setCaption(currentProduct.getName());
        photoMessage.setPhoto(currentProduct.getImage());
        return photoMessage;
    }

}
