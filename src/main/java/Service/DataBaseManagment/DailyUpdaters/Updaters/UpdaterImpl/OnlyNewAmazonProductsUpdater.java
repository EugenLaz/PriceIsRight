package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper.AmazonProductGenerator;
import Service.DataBaseManagment.dao.ProductDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OnlyNewAmazonProductsUpdater implements DailyUpdater {
    Logger logger = LogManager.getLogger(OnlyNewAmazonProductsUpdater.class);

    private ProductDao dao;

    public OnlyNewAmazonProductsUpdater() {
        dao = new ProductDao();
    }

    @Override
    public void update() {

        logger.info(OnlyNewAmazonProductsUpdater.class + " has been invoked to update DataBase.");

        ProductGenerator provider;

        provider = new AmazonProductGenerator();
        provider.getRandomProducts(AMAZON_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));
    }
}
