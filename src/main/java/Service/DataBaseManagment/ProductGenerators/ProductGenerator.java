package Service.DataBaseManagment.ProductGenerators;

import entity.Product;

import java.util.List;

public interface ProductGenerator {
    List<Product> getRandomProducts(int count);
}
