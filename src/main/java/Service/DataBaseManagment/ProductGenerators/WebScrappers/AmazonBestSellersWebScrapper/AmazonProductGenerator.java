package Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper;

import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import entity.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonProductGenerator implements ProductGenerator {

    @Override
    public List<Product> getRandomProducts(int count) {
        List<Product> result = new ArrayList<>();
        Product product;
        for (int i = count; i > 0; i--) {
            product = getProduct();
            if (product != null) {
                result.add(product);
            } else {
                count++;
            }
        }
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
        System.out.println("got new product");
        Document doc = null;
        try {
            doc = Jsoup.connect(AmazonBestSellerCategories.getRandomLink()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements products = doc.getElementsByClass("aok-inline-block zg-item");
        Element element = products.get((int) (Math.random() * products.size()));
        return convertElementToProduct(element);
    }

}


