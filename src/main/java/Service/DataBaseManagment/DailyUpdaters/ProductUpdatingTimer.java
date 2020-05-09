package Service.DataBaseManagment.DailyUpdaters;

import Service.DataBaseManagment.DailyUpdaters.Updaters.Util.UpdaterProxy;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class ProductUpdatingTimer {
    AtomicReference<UpdaterProxy> updaterProxyReference;

    public ProductUpdatingTimer(AtomicReference<UpdaterProxy> upadterReference) {
        this.updaterProxyReference = upadterReference;
    }

    public void start() {
        System.out.println(updaterProxyReference.get().getUpdater().getClass().toString());
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + new Date());
                updaterProxyReference.get().getUpdater().update();
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}
