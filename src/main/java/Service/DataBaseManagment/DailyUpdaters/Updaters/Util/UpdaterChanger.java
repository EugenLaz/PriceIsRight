package Service.DataBaseManagment.DailyUpdaters.Updaters.Util;

import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.OnlyNewAmazonProductsUpdater;
import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.OnlyNewTescoProductsUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdaterChanger implements Runnable {
    static Logger logger = LogManager.getLogger(UpdaterChanger.class);
    private final String changeUpdaterTemplate = "priceIsRighter -changeUpdater %s";
    private UpdaterProxy proxy;

    public UpdaterChanger(UpdaterProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void run() {
        StringBuilder logMessage = new StringBuilder("Daily Updater changed from " + proxy.getUpdater().getClass().getSimpleName() + " to ");
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while (true) {
            try {
                userInput = buffReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (userInput.equals(String.format(changeUpdaterTemplate, "onlyNewAmazon"))) {
                proxy.changeUpdater(new OnlyNewAmazonProductsUpdater());
                logMessage.append(proxy.getUpdater().getClass().getSimpleName());
                logger.info(logMessage);
            } else if (userInput.equals(String.format(changeUpdaterTemplate, "onlyNewTesco"))) {
                proxy.changeUpdater(new OnlyNewTescoProductsUpdater());
                logMessage.append(proxy.getUpdater().getClass().getSimpleName());
                logger.info(logMessage);
            } else System.out.println("wrong command");
        }
    }
}
