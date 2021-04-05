import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker { //класс для получения connection
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "O12345";
    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //подключение к БД
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {  //геттер для получения connection
        return connection;
    }

    public void closeConnection() { //метод для закрытия соединения
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}