package Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper;

import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonProductGenerator implements ProductGenerator {
    Logger logger = LogManager.getLogger(AmazonProductGenerator.class);

    @Override
    public List<Product> getRandomProducts(int count) {
        logger.info(AmazonProductGenerator.class.getSimpleName() + " Started ");
        List<Product> result = new ArrayList<>();
        Product product;
        for (int i = 0; i < count; i++) {
            product = getProduct();
            if (product != null) {
                result.add(product);
            } else {
                i--;
            }
        }
        logger.info(AmazonProductGenerator.class.getSimpleName() + "Successfully generated " + count + " products from Amazon");
        return result;
    }

    private Product convertElementToProduct(Element product) {
        String name = product.getElementsByClass("p13n-sc-truncate").text();
        String url = product.getElementsByAttribute("src").attr("src");
        String price = product.getElementsByClass("p13n-sc-price").text().replaceAll("\\$", "");
        double dPriceValue;
        if (price.length() < 1) {
            return null;
        } else if (price.contains(" ")) {
            dPriceValue = Double.valueOf(price.split(" ")[0]);
        } else {
            dPriceValue = Double.valueOf(price);
        }

        return new Product(name, dPriceValue, url);
    }

    private Product getProduct() {
        Document doc = null;
        String link = AmazonBestSellerCategories.getRandomLink();
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements products = doc.getElementsByClass("aok-inline-block zg-item");
        Element element = products.get((int) (Math.random() * products.size()));
        return convertElementToProduct(element);
    }

}


