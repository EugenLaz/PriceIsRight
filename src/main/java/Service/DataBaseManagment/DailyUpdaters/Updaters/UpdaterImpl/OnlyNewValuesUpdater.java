package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper.AmazonProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper.TescoProductGenerator;
import Service.DataBaseManagment.dao.ProductDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OnlyNewValuesUpdater implements DailyUpdater {
    Logger logger = LogManager.getLogger(OnlyNewValuesUpdater.class);

    private ProductDao dao;

    public OnlyNewValuesUpdater() {
        dao = new ProductDao();
    }

    @Override
    public void update() {
        logger.info(OnlyNewValuesUpdater.class + " has been invoked to update DataBase.");

        ProductGenerator provider;

        provider = new AmazonProductGenerator();
        provider.getRandomProducts(AMAZON_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));

        provider = new TescoProductGenerator();
        provider.getRandomProducts(TESCO_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));
    }
}
