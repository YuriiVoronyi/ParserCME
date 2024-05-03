package pages.autorisation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AutorisationPage extends BasePage {

    //private String login;
    //private String password;

    public AutorisationPage(WebDriver driver) {
        super(driver);
    }

    // Здесь надо прописать логин и пароль

    @FindBy(id = "user")
    WebElement formslogin;

    @FindBy(id = "pwd")//
    WebElement formspassword;

    public AutorisationPage enterAutorisation() {
        type(formslogin,login);
        type(formspassword,password);
        return this;
    }

    @FindBy(id = "loginBtn")
    WebElement loginbutton;

    public DuoPush pressLoginBtn() {
        click(loginbutton);
        return new DuoPush(driver);
    }

}
