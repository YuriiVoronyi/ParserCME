package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Установка максимального времени ожидания в 60 секунд
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "ctl08_hlProductArrow")
    WebElement click1;

    public HomePage choiceHomeMenu1() {
        // Ожидание появления элемента на следующей странице (замените локатор на нужный)
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl08_hlProductArrow")));
        nextElement.click();
        //click(click1);
        return this;
    }

    @FindBy(css = ".groups div [groupid='3']")
    WebElement click2;

    public HomePage choiceHomeMenu2() {
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".groups div [groupid='3']")));
        nextElement.click();
        //click(click2);
        return this;
    }

    @FindBy(css = "[title='E-mini S&P 500']")
    WebElement click3;

    public QuikStrike getSP500() {
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='E-mini S&P 500']")));
        nextElement.click();
        //click(click3);
        return new QuikStrike(driver);
    }

}
