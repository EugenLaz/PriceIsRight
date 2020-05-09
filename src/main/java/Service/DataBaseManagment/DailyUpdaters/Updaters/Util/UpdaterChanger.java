package Service.DataBaseManagment.DailyUpdaters.Updaters.Util;

import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.OnlyNewAmazonProductsUpdater;
import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.OnlyNewTescoProductsUpdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdaterChanger implements Runnable{

    private UpdaterProxy proxy;

    private final String changeUpdaterTemplate = "priceIsRighter -changeUpdater %s";

    public UpdaterChanger(UpdaterProxy proxy){
        this.proxy = proxy;
    }
    @Override
    public void run() {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while (true){
            try {
                userInput = buffReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (userInput.equals(String.format(changeUpdaterTemplate, "onlyNewAmazon"))){
                System.out.println("got the command");
                proxy.changeUpdater(new OnlyNewAmazonProductsUpdater());
            }
            else if(userInput.equals(String.format(changeUpdaterTemplate, "onlyNewTesco"))){
                proxy.changeUpdater(new OnlyNewTescoProductsUpdater());
            }
            else System.out.println("wrong command");
        }
    }
}
