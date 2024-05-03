package pages.callput;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.OpenInteresSB;
import repositories.AppRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ShowCallPutEOM extends BasePage {

    public ShowCallPutEOM(WebDriver driver) {
        super(driver);
    }

    AppRepository appRepository = new AppRepository();

    @FindBy(xpath = "//*[@id='MainContent_ucViewControl_OpenInterestV2_divTabContent']/div/div[2]/div/div[1]/table")
    WebElement tblcall;

    @FindBy(xpath = "//*[@id='MainContent_ucViewControl_OpenInterestV2_divTabContent']/div/div[2]/div/div[2]/table")
    WebElement tblput;

    @FindBy(xpath = "//*[@id='MainContent_ucViewControl_OpenInterestV2_lbOIChart']")
    WebElement oi_sb;

    @FindBy(xpath = "//*[@id='MainContent_ucViewControl_OpenInterestV2_ucOIVC_ucCallTable_lvOpenInterest_trHeader2']/th[2]")
    WebElement dateofdata;

    public OpenInteresSB loadingCallPut(BasePage obj) {
        try {
            obj.setDateOfData(dateofdata.getText());
            Connection connection = appRepository.getConnection();
            if (tblcall != null) {
                String nameOfTable = "eom_calls";

                // Удаление всех строк из таблицы
                String deleteQuery = "DELETE FROM " + nameOfTable;
                connection.prepareStatement(deleteQuery).executeUpdate();

                // Сброс автоинкрементного поля до начального значения
                String resetQuery = "ALTER TABLE " + nameOfTable + " AUTO_INCREMENT = 1";
                connection.prepareStatement(resetQuery).executeUpdate();

                String query = "INSERT INTO " + nameOfTable + " (id, strike_price, vol, month1, month2, chg) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                // Находим все строки в таблице
                List<WebElement> rows = tblcall.findElements(By.tagName("tr"));

                // Проходимся по каждой строке данных и добавляем их в таблицу базы данных.
                for (int i = 3; i < rows.size(); i++) {
                    // Получаем текстовое содержимое ячеек в строке
                    List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

                    // Получаем значения из каждой ячейки и добавляем их в запрос batch insert
                    statement.setInt(1, 0);  // Устанавливаем значение из столбца "id"

                    int value2 = Integer.parseInt(cells.get(0).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement.setInt(2, value2); // Устанавливаем значение из столбца "strike_price"

                    int value3 = Integer.parseInt(cells.get(1).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement.setInt(3, value3); // Устанавливаем значение из столбца "vol"

                    int value4 = Integer.parseInt(cells.get(2).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement.setInt(4, value4); // Устанавливаем значение из столбца "month1"

                    int value5 = Integer.parseInt(cells.get(3).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement.setInt(5, value5); // Устанавливаем значение из столбца "month2"

                    statement.setString(6, cells.get(4).getText());  // Устанавливаем значение из столбца "chg"
                    statement.addBatch();
                }
                // Выполняем пакетную вставку
                statement.executeBatch();
            } else {
                System.out.println("Элемент tblcall не найден на странице");
            }
//=============================================================================================
            if (tblput != null) {
                String nameOfTable2 = "eom_puts";

                // Удаление всех строк из таблицы
                String deleteQuery2 = "DELETE FROM " + nameOfTable2;
                connection.prepareStatement(deleteQuery2).executeUpdate();

                // Сброс автоинкрементного поля до начального значения
                String resetQuery2 = "ALTER TABLE " + nameOfTable2 + " AUTO_INCREMENT = 1";
                connection.prepareStatement(resetQuery2).executeUpdate();

                String query2 = "INSERT INTO " + nameOfTable2 + " (id, strike_price, vol, month1, month2, chg) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(query2);

                // Находим все строки в таблице
                List<WebElement> rows2 = tblput.findElements(By.tagName("tr"));

                // Проходимся по каждой строке данных и добавляем их в таблицу базы данных.
                for (int i = 3; i < rows2.size(); i++) {
                    // Получаем текстовое содержимое ячеек в строке
                    List<WebElement> cells2 = rows2.get(i).findElements(By.tagName("td"));

                    // Получаем значения из каждой ячейки и добавляем их в запрос batch insert
                    statement2.setInt(1, 0);  // Устанавливаем значение из столбца "id"

                    int value22 = Integer.parseInt(cells2.get(0).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement2.setInt(2, value22); // Устанавливаем значение из столбца "strike_price"

                    int value32 = Integer.parseInt(cells2.get(1).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement2.setInt(3, value32); // Устанавливаем значение из столбца "vol"

                    int value42 = Integer.parseInt(cells2.get(2).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement2.setInt(4, value42); // Устанавливаем значение из столбца "month1"

                    int value52 = Integer.parseInt(cells2.get(3).getText().replaceAll("\\s+", "")); // Удаляем пробелы из строки перед преобразованием в целое число
                    statement2.setInt(5, value52); // Устанавливаем значение из столбца "month2"

                    statement2.setString(6, cells2.get(4).getText());  // Устанавливаем значение из столбца "chg"
                    statement2.addBatch();
                }
                // Выполняем пакетную вставку
                statement2.executeBatch();
            } else {
                System.out.println("Элемент tblput не найден на странице");
            }

            // Закрываем соединение с базой данных
            connection.close();
            System.out.println("EOM calls and puts loaded into database.");
            System.out.println("================================================================");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        click(oi_sb);
        pause(5000);

        return new OpenInteresSB(driver);
    }
}
