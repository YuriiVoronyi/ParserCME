package repositories;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppRepository {

    protected static String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver"; // Где лежит драйвер подключения к MySQL
    protected static String DB_ADDRESS = "jdbc:mysql://localhost:3306/"; // Путь, где лежит база данных
    protected static String DB_NAME = "cme_options";                     // Наименование БД
    protected static String DB_USERNAME = "root";                        // Логин пользователя
    protected static String DB_PASSWORD = "qwerty007";                   // Пароль пользователя

    //Метод, который подключается к БД и возвращает объект подключения
    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH); // Загружаем драйвер в память нашего приложения
            String dbUrl = String.format("%s%s?user=%s&password=%s", DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD); // Подключение к БД
            return DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
