package Service.productProviders;

import Service.DataBaseManagment.dao.ProductDao;
import Service.Localization.PriceConvertor;
import entity.Product;

import java.util.Locale;

public class LocalizedPriceProductProvider implements ProductProvider {


    private final ProductDao dao;
    private Locale locale;

    public LocalizedPriceProductProvider(Locale locale) {
        this.locale = locale;
        dao = new ProductDao();
    }

    @Override
    public Product getProduct() {
        Product product = dao.getRandomProduct();
        PriceConvertor.convertFromUsd(product,locale);
        return product;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
