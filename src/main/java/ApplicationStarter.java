import Service.DataBaseManagment.DailyUpdaters.ProductUpdatingTimer;
import Service.DataBaseManagment.DailyUpdaters.Updaters.Util.UpdaterChanger;
import Service.DataBaseManagment.DailyUpdaters.Updaters.Util.UpdaterProxy;
import Service.Localization.PriceConvertor;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.TelegramBot.Bot;
import Service.productProviders.LocalizedPriceProductProvider;
import Service.productProviders.ProductProvider;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.atomic.AtomicReference;

public class ApplicationStarter {

    private static PriceConvertor convertor;
    private static ProductGenerator scrapper;

    public static void main(String[] args) throws InterruptedException {
        start();
    }


    public static void start() {
        UpdaterProxy proxy = new UpdaterProxy();
        UpdaterChanger terminalInputListener = new UpdaterChanger(proxy);

        ProductUpdatingTimer updatesTimer = new ProductUpdatingTimer(new AtomicReference<>(proxy));

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            return;
        }

//        updatesTimer.start();
        terminalInputListener.run();
    }
}
