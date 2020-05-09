package Service.DataBaseManagment.DailyUpdaters.Updaters.Util;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl.DefaultUpdater;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

public class UpdaterProxy {

    private AtomicReference<DailyUpdater> updaterReferenec;

    public UpdaterProxy(){
        updaterReferenec = new AtomicReference<>(new DefaultUpdater());
    }

    public DailyUpdater getUpdater(){
        return updaterReferenec.get();
    }

    public void changeUpdater(DailyUpdater updater){
        updaterReferenec.set(updater);
//        updaterReferenec.setRelease(updater);
    }

}
