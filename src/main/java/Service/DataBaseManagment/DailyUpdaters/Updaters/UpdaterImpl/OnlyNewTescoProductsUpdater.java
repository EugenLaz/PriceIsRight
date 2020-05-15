package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper.TescoProductGenerator;
import Service.DataBaseManagment.dao.ProductDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OnlyNewTescoProductsUpdater implements DailyUpdater {
    Logger logger = LogManager.getLogger(OnlyNewTescoProductsUpdater.class);

    private ProductDao dao;

    public OnlyNewTescoProductsUpdater() {
        dao = new ProductDao();
    }

    @Override
    public void update() {
        logger.info(OnlyNewTescoProductsUpdater.class + " has been invoked to update DataBase.");

        ProductGenerator provider;

        provider = new TescoProductGenerator();
        provider.getRandomProducts(TESCO_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));
    }
}
