package Service.DataBaseManagment.DailyUpdaters.Updaters.Util;

import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.OnlyNewAmazonProductsUpdater;
import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.OnlyNewValuesUpdater;

public class UpdatersFactory {

    public static OnlyNewValuesUpdater getOnlyNewUpdater(){
        return new OnlyNewValuesUpdater();
    }
}
