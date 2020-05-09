package Service.DataBaseManagment.ProductGenerators.WebScrappers.TescoFoodsWebScrapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum TescoCategories {

    FreshFood("https://www.tesco.com/groceries/en-GB/shop/fresh-food/all?page=", 140),
    Bakery("https://www.tesco.com/groceries/en-GB/shop/fresh-food/all?page=", 25),
    FrozenFood("https://www.tesco.com/groceries/en-GB/shop/frozen-food/all?page=", 35),
    FoodCupboard("https://www.tesco.com/groceries/en-GB/shop/food-cupboard/all?page=", 240),
    Drinks("https://www.tesco.com/groceries/en-GB/shop/drinks/all?page=", 110),
    Baby("https://www.tesco.com/groceries/en-GB/shop/baby/all?page=", 35),
    HealthBeauty("https://www.tesco.com/groceries/en-GB/shop/health-and-beauty/all?page=", 140),
    Pets("https://www.tesco.com/groceries/en-GB/shop/pets/all?page=", 30),
    HouseHold("https://www.tesco.com/groceries/en-GB/shop/household/all?page=", 40),
    HomeEnts("https://www.tesco.com/groceries/en-GB/shop/home-and-ents/all?page=", 160);

    private static final List<TescoCategories> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private final String link;
    private int lastPageIndex;


    TescoCategories(String link, int lastPageIndex) {
        this.link = link;
        this.lastPageIndex = lastPageIndex;
    }

    public static String getRandomLink() {
        TescoCategories categorie = VALUES.get(ThreadLocalRandom.current().nextInt(VALUES.size()));
        String link = categorie.getLink()
                + ThreadLocalRandom.current().nextInt(1, categorie.lastPageIndex);
        return link;
    }

    private String getLink() {
        return this.link;
    }

}
