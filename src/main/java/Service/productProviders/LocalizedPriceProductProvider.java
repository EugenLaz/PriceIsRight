package Service.productProviders;

import Service.DataBaseManagment.dao.ProductDao;
import Service.Localization.PriceConvertor;
import entity.Product;

public class LocalizedPriceProductProvider implements ProductProvider {

    private final ProductDao dao;

    public LocalizedPriceProductProvider() {
        dao = new ProductDao();
    }

    @Override
    public Product getProduct() {
        Product product = dao.getRandomProduct();
        PriceConvertor.convertFromUsdToLocal(product);
        return product;
    }
}
