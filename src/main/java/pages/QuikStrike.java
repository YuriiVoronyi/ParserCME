package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QuikStrike extends BasePage {

    public QuikStrike(WebDriver driver) {
        super(driver);
    }

    // Установка максимального времени ожидания в 60 секунд
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(css = "[id='ctl00_ucMenuBar_lvMenuBar_ctrl2_lbMenuItem']")
    WebElement openinteres;

    public OpenInteres getOpenInteres() {
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='ctl00_ucMenuBar_lvMenuBar_ctrl2_lbMenuItem']")));
        nextElement.click();
        //click(openinteres);
        return new OpenInteres(driver);
    }

    @FindBy(css = "[id='ctl00_ucMenuBar_lvMenuBar_ctrl5_lbMenuItem']")
    WebElement pricingsheets;

    public PricingSheets clickPricingSheets() {
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='ctl00_ucMenuBar_lvMenuBar_ctrl5_lbMenuItem']")));
        nextElement.click();
        //click(pricingsheets);
        return new PricingSheets(driver);
    }
}
