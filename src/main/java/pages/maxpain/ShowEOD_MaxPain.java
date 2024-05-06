package pages.maxpain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.OpenInteresSB;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowEOD_MaxPain extends BasePage {

    private BasePage balObj;

    public ShowEOD_MaxPain(WebDriver driver, BasePage balObj) {
        super(driver);
        this.balObj = balObj;
    }

    // Установка максимального времени ожидания в 60 секунд
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    //Локатор кнопки модального окна
    private By modalButtonLocator = By.xpath("//*[@id='MainContent_ucViewControl_OpenInterestV2_ucChartTB_hlMaxPain']");
    WebElement modalButton = driver.findElement(modalButtonLocator);

    // Локатор элемента внутри модального окна
    private By modalElementLocator = By.xpath("//*[@class='highcharts-subtitle']");

    // Локатор кнопки закрытия модального окна
    private By modalCloseWinLocator = By.xpath("//*[@id='globalContextPopup']/div[1]/div[2]/a");
    WebElement closeButton = driver.findElement(modalCloseWinLocator);

    public static int extractNumber(String text) {
        // Паттерн для поиска числа в строке
        Pattern pattern = Pattern.compile("\\d+");

        // Создаем Matcher для заданного текста и паттерна
        Matcher matcher = pattern.matcher(text);

        // Ищем соответствие паттерну
        if (matcher.find()) {
            // Если найдено, возвращаем найденное число
            return Integer.parseInt(matcher.group());
        } else {
            // В противном случае, возвращаем -1 или генерируем исключение
            throw new IllegalArgumentException("Number not found in the text: " + text);
        }
    }

    public OpenInteresSB getMaxPain(BasePage obj) {

        //modalButton.click();// Открываем попап (фрейм с балансом)
        WebElement nextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='MainContent_ucViewControl_OpenInterestV2_ucChartTB_hlMaxPain']")));
        nextElement.click();// Открываем попап (фрейм с балансом)

        //pause(5000);
        WebElement nextElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainFrame")));
        driver.switchTo().frame("mainFrame");// Перехожу на фрейм по идентификатору

        //pause(2000);

        WebElement nextElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='highcharts-subtitle']")));
        WebElement element = driver.findElement(modalElementLocator);// Нахожу строку с балансом по локатору
        int maxpain = extractNumber(element.getText().trim());// Выдёргиваю из строки значение баланса

        obj.setEod(maxpain);

        System.out.println("The EOD balance is found, it is equal to " + maxpain);

        driver.switchTo().defaultContent();// Возвращаюсь с фрейма на основную страницу

        //pause(1000);

        //closeButton.click();// Закрываю попап (фрейм)
        WebElement nextElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='globalContextPopup']/div[1]/div[2]/a")));
        nextElement4.click();

        return new OpenInteresSB(driver);
    }
}
