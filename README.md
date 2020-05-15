# PriceIsRight
 Application was inspired by episode of 'How I met your mother', where Barney Stinson is playing identical game.

## Technologiesg
Maven, Jsoup, Selenium, Log4j2, Hibernate

### Description
Every night application is generating a random products by web-scrapping them from Tesco and Amazon with the help of JSoup or Selenium.

Then they are loaded in database( The way they are saved can be picked at runtime, with the help of console input). 

After that products are retrieved by telegram bot which can be added to telegram chat.

The bot asks the currency, in which the prices will be displayed and guessed.
All price values stored in USD currency, so they are passed through PriceConverting service, before game. It will calculate
evaluate of product, based on current currency

After that the picture and the photo of the product is being sent to chat and the guesses are being made.

The player with closest bid - wins.

### The goal of application
The goal of project is to learn about TelegramBotsApi and WebScrapping with different tools.


