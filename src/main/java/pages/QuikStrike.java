package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuikStrike extends BasePage {

    public QuikStrike(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[id='ctl00_ucMenuBar_lvMenuBar_ctrl2_lbMenuItem']")
    WebElement openinteres;

    public OpenInteres getOpenInteres() {
        click(openinteres);
        return new OpenInteres(driver);
    }

    @FindBy(css = "[id='ctl00_ucMenuBar_lvMenuBar_ctrl5_lbMenuItem']")
    WebElement pricingsheets;

    public PricingSheets clickPricingSheets() {
        click(pricingsheets);
        return new PricingSheets(driver);
    }
}
