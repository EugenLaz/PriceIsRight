package Service.DataBaseManagment.DailyUpdaters;

import Service.DataBaseManagment.DailyUpdaters.Updaters.Util.UpdaterProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class ProductUpdatingTimer {
    static Logger logger = LogManager.getLogger(ProductUpdatingTimer.class);

    AtomicReference<UpdaterProxy> updaterProxyReference;

    public ProductUpdatingTimer(AtomicReference<UpdaterProxy> upadterReference) {
        this.updaterProxyReference = upadterReference;
    }

    public void start() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                logger.info("Daily Task completed with " + updaterProxyReference.get().getUpdater().getClass());
                updaterProxyReference.get().getUpdater().update();
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}
