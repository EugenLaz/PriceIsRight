package Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper;

import Service.Localization.PriceConvertor;
import Service.DataBaseManagment.ProductGenerators.ProductGenerator;
import entity.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TescoProductGenerator implements ProductGenerator {

    @Override
    public List<Product> getRandomProducts(int count) {
        List<Product> result = new ArrayList<>();

        File file = new File("C:/Users/Евгений/Desktop/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        Product product;
        try {
            for (int i = count; i > 0; i--) {
                String link = TescoCategories.getRandomLink();
                System.out.println(link);
                driver.get(link);
                try {
                    product = getProduct(driver.findElements(By.className("tile-content")));
                    result.add(product);
                } catch (Exception e) {
                    count++;
                }

            }
        } finally {
            driver.quit();
        }
        return result;
    }

    public Product getProduct(List<WebElement> products) {

        WebElement product = products.get((int) Math.random() * products.size());
        String name = product.findElement(By.xpath("//a[@data-auto='product-tile--title']")).getText().replaceAll("Tesco", "");
        String price = product.findElement(By.xpath("//span[@data-auto='price-value']")).getText();
        String imgUrl = product.findElement(By.className("product-image__container")).findElement(By.tagName("img")).getAttribute("src");

        Product result = new Product(name,Double.valueOf(price.replace("£", "")),imgUrl);
        PriceConvertor.convertToUsd(result, Locale.UK);

        return result;

    }

}
