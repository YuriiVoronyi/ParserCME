package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {

    static WebDriver driver;

    //@BeforeSuite
    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();//Инициализируем хромдрайвер
        driver.get("https://cmegroup-sso.quikstrike.net/User/QuikStrikeView.aspx?init=");//Открываем наш сайт
        driver.manage().window().maximize();//Разворачиваем окно браузера в полный экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //@AfterSuite(enabled = false)
    @AfterMethod(enabled = false)//браузер НЕ закрываем
    public void tearDown() {
        driver.quit();
    }//quit-закрывает браузер, close-закрывает текущую вкладку

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
