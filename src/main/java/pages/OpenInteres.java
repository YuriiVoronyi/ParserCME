package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenInteres extends BasePage{

    public OpenInteres(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[id='MainContent_ucViewControl_OpenInterestV2_lbOITable']")
    WebElement summary;

    public Summary getSummary() {
        click(summary);
        return new Summary(driver);
    }

}
