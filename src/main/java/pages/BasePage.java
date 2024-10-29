package pages;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class BasePage {//Родительский класс для всех страниц

    private int eow; // Баланс недели
    private int eoa; // Баланс понедельника
    private int eob; // Баланс вторника
    private int eoc; // Баланс среды
    private int eod; // Баланс четверга
    private int eom; // Баланс месяца
    private String dateOfData; // Дата, по которым загружаются данные

    public WebDriver driver;

    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);//Инициализируем страницы и элементы
        js = (JavascriptExecutor) driver;
    }

    public BasePage() {
    }

    public String getDateOfData() {
        return dateOfData;
    }

    public void setDateOfData(String dateOfData) {
        this.dateOfData = dateOfData;
    }

    public int getEow() {
        return eow;
    }

    public void setEow(int eow) {
        this.eow = eow;
    }

    public int getEoa() {
        return eoa;
    }

    public void setEoa(int eoa) {
        this.eoa = eoa;
    }

    public int getEob() {
        return eob;
    }

    public void setEob(int eob) {
        this.eob = eob;
    }

    public int getEoc() {
        return eoc;
    }

    public void setEoc(int eoc) {
        this.eoc = eoc;
    }

    public int getEod() {
        return eod;
    }

    public void setEod(int eod) {
        this.eod = eod;
    }

    public int getEom() {
        return eom;
    }

    public void setEom(int eom) {
        this.eom = eom;
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void click(WebElement element) {//Клик по элементу
        element.click();
    }

    public void type(WebElement element, String text) {//В элемент печатаем текст
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void clickWithJS(WebElement element, int x, int y) {//скролим окно, а потом кликаем
        js.executeScript("window.scrollBy(" + x +"," + y + ")");
        click(element);
    }

    public String makeScreen() {//Делаем скрин-шот и сохраняем его в папке по указанному пути в формате png
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen_" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return screenshot.getAbsolutePath();
    }
}
