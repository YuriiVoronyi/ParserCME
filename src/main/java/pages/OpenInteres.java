package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenInteres extends BasePage{

    public OpenInteres(WebDriver driver) {
        super(driver);
    }

    // Установка максимального времени ожидания в 60 секунд
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(css = "[id='MainContent_ucViewControl_OpenInterestV2_lbOITable']")
    WebElement summary;

    public Summary getSummary() {
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='MainContent_ucViewControl_OpenInterestV2_lbOITable']")));
        nextElement.click();
        //click(summary);
        return new Summary(driver);
    }

}
