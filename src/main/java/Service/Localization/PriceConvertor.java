package Service.Localization;


import entity.Product;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Currency;
import java.util.Locale;

public class PriceConvertor {

    private static final String fromUsdTemplate = "https://www.google.com/search?q=USD+TO+%s";
    private static final String toUsdTemplate = "https://www.google.com/search?q=%s+TO+USD";


    public static void convertFromUsd(Product product, Locale locale) {
        String url = String.format(fromUsdTemplate, Currency.getInstance(locale));
        if(locale.equals(Locale.US)){
            return;
        }
        Double currency = null;
        try {
            currency = Double.valueOf(
                    Jsoup.connect(url).get().getElementsByAttribute("data-precision").text().replace(',', '.'));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        product.setPrice(currency * product.getPrice());
    }

    public static void convertToUsd(Product product, Locale languageLocale) {
        String url = String.format(toUsdTemplate, Currency.getInstance(languageLocale));
        Double cuurency = null;
        try {
            cuurency = Double.valueOf(
                    Jsoup.connect(url).get().getElementsByAttribute("data-precision").text()
                            .replace(',', '.'));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setPrice(product.getPrice() * cuurency);
    }

}
