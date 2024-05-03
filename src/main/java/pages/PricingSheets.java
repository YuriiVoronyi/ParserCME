package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.board.*;
import repositories.AppRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class PricingSheets extends BasePage {

    public PricingSheets(WebDriver driver) {
        super(driver);
    }

    AppRepository appRepository = new AppRepository();

    String numberString;
    //
//    private By clickEOM_Locator = By.cssSelector("[id='ctl00_ucSelector_lvGroupsExpirations_ctrl6_lvExpirations_ctrl0_lbExpiration']");
//
//    WebElement choiceEOM_Locator = driver.findElement(clickEOM_Locator);

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_lvGroupsExpirations_ctrl6_lvExpirations_ctrl0_lbExpiration']/div[1]")
    WebElement clickEOM_Locator;

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_lvGroupsExpirations_ctrl5_lvExpirations_ctrl0_lbExpiration']/div[1]")
    WebElement clickEOD_Locator;

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_lvGroupsExpirations_ctrl4_lvExpirations_ctrl0_lbExpiration']/div[1]")
    WebElement clickEOC_Locator;

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_lvGroupsExpirations_ctrl3_lvExpirations_ctrl0_lbExpiration']/div[1]")
    WebElement clickEOB_Locator;

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_lvGroupsExpirations_ctrl2_lvExpirations_ctrl0_lbExpiration']/div[1]")
    WebElement clickEOA_Locator;

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_hlExpiration']")
    WebElement openmenu;

    @FindBy(xpath = "//*[@id='pricing-sheet']")
    WebElement tblPricingSheet;

    //айдишка выпадающего меню по количеству выводимых строк: MainContent_ucViewControl_OpenInterestV2_ucOITB_ddlStrikes
    public PricingSheets selectAllRows() {
        WebElement dropdownMenu = driver.findElement(By.id("MainContent_ucViewControl_EssentialPricingSheet_ddlStrikeCount"));
        Select dropdown = new Select(dropdownMenu);
        dropdown.selectByVisibleText("(All)");
        return this;
    }

    public PricingSheets loadingBoardEOW() {
        selectAllRows();
        pause(5000);
        try {
            Connection connection = appRepository.getConnection();
            if (tblPricingSheet != null) {

                // Удаление всех строк из таблицы
                String deleteQuery = "DELETE FROM eow_board";
                connection.prepareStatement(deleteQuery).executeUpdate();

                // Сброс автоинкрементного поля до начального значения
                String resetQuery = "ALTER TABLE eow_board AUTO_INCREMENT = 1";
                connection.prepareStatement(resetQuery).executeUpdate();

                String query = "INSERT INTO eow_board (id, volatility, delta_call, call_settle, `call`, strike, put, put_settle, delta_put, straddle, gamma, vega, theta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                // Находим все строки в таблице
                List<WebElement> rows = tblPricingSheet.findElements(By.tagName("tr"));

                // Проходимся по каждой строке данных и добавляем их в таблицу базы данных.
                for (int i = 1; i < rows.size() - 1; i++) {
                    // Получаем текстовое содержимое ячеек в строке
                    List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

                    // Получаем значения из каждой ячейки и добавляем их в запрос batch insert
                    statement.setInt(1, 0);  // Устанавливаем значение из столбца "id"

                    numberString = cells.get(0).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value2 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(2, value2); // Устанавливаем значение из столбца "VOLATILITY"

                    numberString = cells.get(1).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value3 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(3, value3); // Устанавливаем значение из столбца "DELTA"

                    numberString = cells.get(2).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value4 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(4, value4); // Устанавливаем значение из столбца "CALL SETTLE"

                    numberString = cells.get(3).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value5 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(5, value5); // Устанавливаем значение из столбца "CALL"

                    int value6 = Integer.parseInt(cells.get(4).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement.setInt(6, value6); // Устанавливаем значение из столбца "STRIKE"

                    numberString = cells.get(5).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value7 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(7, value7); // Устанавливаем значение из столбца "PUT"

                    numberString = cells.get(6).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value8 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(8, value8); // Устанавливаем значение из столбца "PUT SETTLE"

                    numberString = cells.get(7).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value9 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(9, value9); // Устанавливаем значение из столбца "DELTA"

                    numberString = cells.get(8).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value10 = numberString.equals("0") ? BigDecimal.ZERO.setScale(2) : new BigDecimal(numberString).setScale(2);
                    statement.setBigDecimal(10, value10); // Устанавливаем значение из столбца "STRADDLE"

                    numberString = cells.get(9).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value11 = numberString.equals("0") ? BigDecimal.ZERO.setScale(5) : new BigDecimal(numberString).setScale(5);
                    statement.setBigDecimal(11, value11); // Устанавливаем значение из столбца "GAMMA"

                    numberString = cells.get(10).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value12 = numberString.equals("0") ? BigDecimal.ZERO.setScale(3) : new BigDecimal(numberString).setScale(3);
                    statement.setBigDecimal(12, value12); // Устанавливаем значение из столбца "VEGA"

                    numberString = cells.get(11).getText().replaceAll("\\s+", "");// Удаляем пробелы из строки перед преобразованием в число
                    numberString = numberString.replace(",", ".");//заменить запятую на точку
                    BigDecimal value13 = numberString.equals("0") ? BigDecimal.ZERO.setScale(3) : new BigDecimal(numberString).setScale(3);
                    statement.setBigDecimal(13, value13); // Устанавливаем значение из столбца "THETA"

                    statement.addBatch();
                }
                // Выполняем пакетную вставку
                statement.executeBatch();
            } else {
                System.out.println("Элемент tblPricingSheet не найден на странице");
            }
            // Закрываем соединение с базой данных
            connection.close();
            System.out.println("Options board EOW has loaded");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public ShowBoardEOD loadingBoardEOD() {
        click(openmenu);
        pause(5000);
        click(clickEOD_Locator);
        pause(5000);
        return new ShowBoardEOD(driver);
    }

    public ShowBoardEOC loadingBoardEOC() {
        click(openmenu);
        pause(5000);
        click(clickEOC_Locator);
        pause(5000);
        return new ShowBoardEOC(driver);
    }

    public ShowBoardEOB loadingBoardEOB() {
        click(openmenu);
        pause(5000);
        click(clickEOB_Locator);
        pause(5000);
        return new ShowBoardEOB(driver);
    }

    public ShowBoardEOA loadingBoardEOA() {
        click(openmenu);
        pause(5000);
        click(clickEOA_Locator);
        pause(5000);
        return new ShowBoardEOA(driver);
    }

    public ShowBoardEOM loadingBoardEOM() {
        click(openmenu);
        pause(5000);
        click(clickEOM_Locator);
        pause(5000);
        return new ShowBoardEOM(driver);
    }
}
