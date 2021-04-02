import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "O12345";


    public static void main(String[] args) {
        //System.out.println("qwer");
        Connection connection;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed())
                System.out.println("Соединение с БД установлено");
            connection.close();
            if (connection.isClosed())
                System.out.println("Соединение с БД прервано");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
