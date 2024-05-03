package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ctl08_hlProductArrow")
    WebElement click1;

    public HomePage choiceHomeMenu1() {
        click(click1);
        return this;
    }

    @FindBy(css = ".groups div [groupid='3']")
    WebElement click2;

    public HomePage choiceHomeMenu2() {
        click(click2);
        return this;
    }

    @FindBy(css = "[title='E-mini S&P 500']")
    WebElement click3;

    public QuikStrike getSP500() {
        click(click3);
        return new QuikStrike(driver);
    }

}
