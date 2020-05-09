package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.dao.ProductDao;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper.AmazonProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper.TescoProductGenerator;

public class DefaultUpdater implements DailyUpdater {

    private ProductDao dao;

    public DefaultUpdater() {
        dao = new ProductDao();
    }

    public void update() {
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
