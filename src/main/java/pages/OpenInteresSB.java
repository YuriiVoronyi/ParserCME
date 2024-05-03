package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.maxpain.*;
import repositories.AppRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenInteresSB extends BasePage {

    public OpenInteresSB(WebDriver driver) {
        super(driver);
    }

    AppRepository appRepository = new AppRepository();

    private By clickEOW_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl1_lvExpirations_ctrl0_lbExpiration']");
    private By clickEOA_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl2_lvExpirations_ctrl0_lbExpiration']");
    private By clickEOB_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl3_lvExpirations_ctrl0_lbExpiration']");
    private By clickEOC_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl4_lvExpirations_ctrl0_lbExpiration']");
    private By clickEOD_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl5_lvExpirations_ctrl0_lbExpiration']");
    private By clickEOM_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl6_lvExpirations_ctrl0_lbExpiration']");

    WebElement choiceEOW_Locator = driver.findElement(clickEOW_Locator);
    WebElement choiceEOA_Locator = driver.findElement(clickEOA_Locator);
    WebElement choiceEOB_Locator = driver.findElement(clickEOB_Locator);
    WebElement choiceEOC_Locator = driver.findElement(clickEOC_Locator);
    WebElement choiceEOD_Locator = driver.findElement(clickEOD_Locator);
    WebElement choiceEOM_Locator = driver.findElement(clickEOM_Locator);

    private By clickMenuLocator = By.cssSelector("[id='ctl00_ucSelector_imgFilter']");
    WebElement openMenuElement = driver.findElement(clickMenuLocator);

    private By clickPricingSheetsLocator = By.xpath("//*[@id='ctl00_ucMenuBar_lvMenuBar_ctrl5_lbMenuItem']");
    WebElement openPricingSheets = driver.findElement(clickPricingSheetsLocator);

//0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000

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

    //0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000

    public OpenInteresSB getMaxPainEOM(BasePage obj) {

        modalButton.click();// Открываем попап (фрейм с балансом)

        pause(5000);

        driver.switchTo().frame("mainFrame");// Перехожу на фрейм по идентификатору

        WebElement element = driver.findElement(modalElementLocator);// Нахожу строку с балансом по локатору
        int maxpain = extractNumber(element.getText().trim());// Выдёргиваю из строки значение баланса

        obj.setEom(maxpain);

        System.out.println("The EOM balance is found, it is equal to " + maxpain);

        driver.switchTo().defaultContent();// Возвращаюсь с фрейма на основную страницу

        pause(1000);

        closeButton.click();// Закрываю попап (фрейм)

        return this;
    }

    public ShowEOA_MaxPain getMaxPainEOA(BasePage obj) {
        openMenuElement.click();//Открываю меню выбора типа контракта
        pause(2000);
        choiceEOA_Locator.click();
        pause(5000);
        return new ShowEOA_MaxPain(driver, obj);//7777777777777777777777777777777777777777777777777777777777777
    }

    public ShowEOB_MaxPain getMaxPainEOB(BasePage obj) {
        openMenuElement.click();//Открываю меню выбора типа контракта
        pause(2000);
        choiceEOB_Locator.click();
        pause(5000);
        return new ShowEOB_MaxPain(driver, obj);
    }

    public ShowEOC_MaxPain getMaxPainEOC(BasePage obj) {
        openMenuElement.click();//Открываю меню выбора типа контракта
        pause(2000);
        choiceEOC_Locator.click();
        pause(5000);
        return new ShowEOC_MaxPain(driver, obj);
    }

    public ShowEOD_MaxPain getMaxPainEOD(BasePage obj) {
        openMenuElement.click();//Открываю меню выбора типа контракта
        pause(2000);
        choiceEOD_Locator.click();
        pause(5000);
        return new ShowEOD_MaxPain(driver, obj);
    }

    public ShowEOW_MaxPain getMaxPainEOW(BasePage obj) {
        openMenuElement.click();//Открываю меню выбора типа контракта
        pause(2000);
        choiceEOW_Locator.click();
        pause(5000);
        return new ShowEOW_MaxPain(driver, obj);
    }

    public void sendBalancesToDB(BasePage obj) {
        try {
            Connection connection = appRepository.getConnection();

            // Удаление всех строк из таблицы
            String deleteQuery = "DELETE FROM balance";
            connection.prepareStatement(deleteQuery).executeUpdate();

            // Сброс автоинкрементного поля до начального значения
            String resetQuery = "ALTER TABLE balance AUTO_INCREMENT = 1";
            connection.prepareStatement(resetQuery).executeUpdate();

            String query = "INSERT INTO balance (id, eow, eoa, eob, eoc, eod, eom) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Получаем значения из каждой ячейки и добавляем их в запрос batch insert
            statement.setInt(1, 0);  // Устанавливаем значение из столбца "id"

            //int value2 = Integer.parseInt(cells.get(0).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
            statement.setInt(2, obj.getEow()); // Устанавливаем значение из столбца "strike_price"

            //int value3 = Integer.parseInt(cells.get(1).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
            statement.setInt(3, obj.getEoa()); // Устанавливаем значение из столбца "vol"

            //int value4 = Integer.parseInt(cells.get(2).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
            statement.setInt(4, obj.getEob()); // Устанавливаем значение из столбца "month1"

            //int value5 = Integer.parseInt(cells.get(3).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
            statement.setInt(5, obj.getEoc()); // Устанавливаем значение из столбца "month2"

            //int value5 = Integer.parseInt(cells.get(3).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
            statement.setInt(6, obj.getEod()); // Устанавливаем значение из столбца "month2"

            //int value5 = Integer.parseInt(cells.get(3).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
            statement.setInt(7, obj.getEom()); // Устанавливаем значение из столбца "month2"

            statement.addBatch();

            // Выполняем пакетную вставку
            statement.executeBatch();

            // Закрываем соединение с базой данных
            connection.close();
            System.out.println("Contract balances are loaded into the database !!!");
            System.out.println("============================================================");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PricingSheets getPricingSheets() {
        openPricingSheets.click();//Открываю закладку Pricing Sheets
        pause(5000);
        return new PricingSheets(driver);
    }
}
