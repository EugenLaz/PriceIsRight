package Service.DataBaseManagment.DailyUpdaters.Updaters.UpdaterImpl;

import Service.DataBaseManagment.DailyUpdaters.Updaters.DailyUpdater;
import Service.DataBaseManagment.dao.ProductDao;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper.TescoProductGenerator;

public class OnlyNewTescoProductsUpdater implements DailyUpdater {
    private ProductDao dao;

    public OnlyNewTescoProductsUpdater() {
        dao = new ProductDao();
    }

    @Override
    public void update() {
        ProductGenerator provider;

        provider = new TescoProductGenerator();
        provider.getRandomProducts(TESCO_PRODUCT_AMOUNT).forEach(product -> dao.saveProduct(product));
    }
}
