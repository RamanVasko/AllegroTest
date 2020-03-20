package work.allegro;

import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class AllegroTest {

    @BeforeClass
    public static void visitAllegro() {
        Configuration.startMaximized = true;
        open("https://allegro.pl/");
        Configuration.timeout = 8000;
    }

    @Test
    public void testAllegro() {
        $(By.xpath("//div[@class='_9f0v0 _jkrtd _qj95d']//child::button[2]")).click();
        $(By.xpath("//input[@placeholder='czego szukasz?']")).setValue("Iphone 11").pressEnter();
        $(By.xpath("//select[@class='_1h7wt _k70df _7qjq4 _27496_3VqWr']//option[contains(text(),'cena: od najwy')]")).click();
        System.out.println("Quantity of filtered elements on the page: " + $$(By.xpath("//article[@data-analytics-view-custom-page='1']")).size());
        $(By.xpath("//div[@id='opbox-listing-filters']//span[contains(text(),'czarny')]")).click();
        System.out.println("The highest price is: " + $(By.xpath("(//span[@class='_9c44d_1zemI'])[6]")).getText());

        String maxPrice = $(By.xpath("(//span[@class='_9c44d_1zemI'])[6]")).getText();
        try {
            double d = Double.parseDouble(maxPrice.replaceAll("[^0-9]", ""));
            System.out.println("The highest price with increasing on 23% is: " + d * 0.0123 + " zł");
        } catch (Exception e) {
            e.printStackTrace();
        }

        $(By.xpath("//h2[@class='_9c44d_LUA1k _9c44d_3ZUx6']")).shouldHave(text("iphone 11"));
    }

    @AfterClass
    public static void tearDown() {
        closeWebDriver();
    }
}