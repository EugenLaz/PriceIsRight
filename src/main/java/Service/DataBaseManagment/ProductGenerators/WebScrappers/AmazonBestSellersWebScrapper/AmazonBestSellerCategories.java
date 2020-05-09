package Service.DataBaseManagment.ProductGenerators.WebScrappers.AmazonBestSellersWebScrapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AmazonBestSellerCategories {

    AmazonDevices("https://www.amazon.com/Best-Sellers/zgbs/amazon-devices/ref=zg_bs_nav_0/135-2472398-1046461"),
    AmazonLaunchpad("https://www.amazon.com/Best-Sellers-Amazon-Launchpad/zgbs/boost/ref=zg_bs_nav_0/135-2472398-1046461"),
    AmazonPantry("https://www.amazon.com/Best-Sellers-Prime-Pantry/zgbs/pantry/ref=zg_bs_nav_0/135-2472398-1046461"),
    Appliances("https://www.amazon.com/Best-Sellers-Appliances/zgbs/appliances/ref=zg_bs_nav_0/135-2472398-1046461"),
    Arts("https://www.amazon.com/Best-Sellers-Arts-Crafts-Sewing/zgbs/arts-crafts/ref=zg_bs_nav_0/135-2472398-1046461"),
    Automotive("https://www.amazon.com/Best-Sellers-Automotive/zgbs/automotive/ref=zg_bs_nav_0/135-2472398-1046461"),
    Baby("https://www.amazon.com/Best-Sellers-Baby/zgbs/baby-products/ref=zg_bs_nav_0/135-2472398-1046461"),
    PersonalCare("https://www.amazon.com/Best-Sellers-Beauty/zgbs/beauty/ref=zg_bs_nav_0/135-2472398-1046461"),
    Books("https://www.amazon.com/best-sellers-books-Amazon/zgbs/books/ref=zg_bs_nav_0/135-2472398-1046461"),
    Camera("https://www.amazon.com/best-sellers-camera-photo/zgbs/photo/ref=zg_bs_nav_0/135-2472398-1046461"),
    CellPhones("https://www.amazon.com/Best-Sellers/zgbs/wireless/ref=zg_bs_nav_0/135-2472398-1046461"),
    ClothingShoes("https://www.amazon.com/Best-Sellers/zgbs/fashion/ref=zg_bs_nav_0/135-2472398-1046461"),
    CollectibleCurrencies("https://www.amazon.com/Best-Sellers-Collectible-Coins/zgbs/coins/ref=zg_bs_nav_0/135-2472398-1046461"),
    Computers("https://www.amazon.com/Best-Sellers-Computers-Accessories/zgbs/pc/ref=zg_bs_nav_0/135-2472398-1046461"),
    Electronics("https://www.amazon.com/Best-Sellers-Electronics/zgbs/electronics/ref=zg_bs_nav_0/135-2472398-1046461"),
    EntertainmentCollectibles("https://www.amazon.com/Best-Sellers-Entertainment-Collectibles/zgbs/entertainment-collectibles/ref=zg_bs_nav_0/135-2472398-1046461"),
    Grocery("https://www.amazon.com/Best-Sellers-Grocery-Gourmet-Food/zgbs/grocery/ref=zg_bs_nav_0/135-2472398-1046461"),
    HandmadeProducts("https://www.amazon.com/Best-Sellers-Handmade/zgbs/handmade/ref=zg_bs_nav_0/135-2472398-1046461"),
    Health("https://www.amazon.com/Best-Sellers-Health-Personal-Care/zgbs/hpc/ref=zg_bs_nav_0/135-2472398-1046461"),
    Home("https://www.amazon.com/Best-Sellers-Home-Kitchen/zgbs/home-garden/ref=zg_bs_nav_0/135-2472398-1046461"),
    Industrial("https://www.amazon.com/Best-Sellers-Industrial-Scientific/zgbs/industrial/ref=zg_bs_nav_0/135-2472398-1046461"),
    KindleStore("https://www.amazon.com/Best-Sellers-Kindle-Store/zgbs/digital-text/ref=zg_bs_nav_0/135-2472398-1046461"),
    Kitchen("https://www.amazon.com/Best-Sellers-Kitchen-Dining/zgbs/kitchen/ref=zg_bs_nav_0/135-2472398-1046461"),
    MusicalInstruments("https://www.amazon.com/Best-Sellers-Musical-Instruments/zgbs/musical-instruments/ref=zg_bs_nav_0/135-2472398-1046461"),
    OfficeProducts("https://www.amazon.com/Best-Sellers-Office-Products/zgbs/office-products/ref=zg_bs_nav_0/135-2472398-1046461"),
    Garden("https://www.amazon.com/Best-Sellers-Garden-Outdoor/zgbs/lawn-garden/ref=zg_bs_nav_0/135-2472398-1046461"),
    PetSupplies("https://www.amazon.com/Best-Sellers-Pet-Supplies/zgbs/pet-supplies/ref=zg_bs_nav_0/135-2472398-1046461"),
    Sports("https://www.amazon.com/Best-Sellers-Sports-Outdoors/zgbs/sporting-goods/ref=zg_bs_nav_0/135-2472398-1046461"),
    SportsCollectibles("https://www.amazon.com/Best-Sellers-Sports-Collectibles/zgbs/sports-collectibles/ref=zg_bs_nav_0/135-2472398-1046461"),
    Tools("https://www.amazon.com/Best-Sellers-Home-Improvement/zgbs/hi/ref=zg_bs_nav_0/135-2472398-1046461"),
    Toys("https://www.amazon.com/Best-Sellers-Toys-Games/zgbs/toys-and-games/ref=zg_bs_nav_0/135-2472398-1046461"),
    VideoGames("https://www.amazon.com/best-sellers-video-games/zgbs/videogames/ref=zg_bs_nav_0/135-2472398-1046461");

    private static final List<AmazonBestSellerCategories> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random random = new Random();

    private final String link;

    AmazonBestSellerCategories(String link) {
        this.link = link;
    }

    public static String getRandomLink() {
        return VALUES.get(random.nextInt(VALUES.size())).getLink();
    }

    private String getLink() {
        return this.link;
    }


}
