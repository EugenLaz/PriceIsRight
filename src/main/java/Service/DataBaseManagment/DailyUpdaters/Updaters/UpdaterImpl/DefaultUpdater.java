package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper.AmazonProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper.TescoProductGenerator;
import Service.DataBaseManagment.dao.ProductDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DefaultUpdater implements DailyUpdater {

    static Logger logger = LogManager.getLogger(DefaultUpdater.class);

    private ProductDao dao;

    public DefaultUpdater() {
        dao = new ProductDao();
    }

    public void update() {
        logger.info(DefaultUpdater.class + " has been invoked to update DataBase.");
        dao.deleteAllProducts();
        fillWithProducts();
    }

    private void fillWithProducts() {
        ProductGenerator provider;

        provider = new AmazonProductGenerator();
        provider.getRandomProducts(AMAZON_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));

        provider = new TescoProductGenerator();
        provider.getRandomProducts(TESCO_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));
    }


}
