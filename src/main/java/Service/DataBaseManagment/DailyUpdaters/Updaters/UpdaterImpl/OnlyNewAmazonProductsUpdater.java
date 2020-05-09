package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.dao.ProductDao;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper.AmazonProductGenerator;

public class OnlyNewAmazonProductsUpdater implements DailyUpdater {
    private ProductDao dao;

    public OnlyNewAmazonProductsUpdater() {
        dao = new ProductDao();
    }

    @Override
    public void update() {
        ProductGenerator provider;

        provider = new AmazonProductGenerator();
        provider.getRandomProducts(AMAZON_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));
    }
}
