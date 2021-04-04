import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "O12345";

    public static void main(String[] args) {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver(); //указал какой JDBC драйвер для взаимодействия будем использовать
            DriverManager.registerDriver(driver);//Этот класс управляет списком драйверов БД. С помощью DM зарегистировал драйвер
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Интерфейс Connection обеспечивает нас методами для работы с БД. Все взаимодействия с БД через Connection
        //connection - получим соединение с БД через зарегистрированый драйвер с URL, UN и PW
        //Statement - создадим statement в текущем connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            //работа с методами execute(), executeUpdate(), executeQuery()
            //statement.execute("INSERT INTO users (name, age, email) VALUES ('Ron', 34, 'emailron.com');"); //универсальный метод для вставки/получения данных. execute() - может получать несколько ResultSet
            //int res = statement.executeUpdate("DELETE FROM users WHERE id > 10"); //с помощью этого метода выполняется INSERT, UPDATE, DELETE. Метод возвращает количество изм строк
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users;"); //выполняет только SELECT. Возвращает ResultSet

            //пример пакетной обработки. Несколько запросов выполняются за один execute. Для этого запросы поместим их в один пакет
            statement.addBatch("INSERT INTO users (name, age, email) VALUES ('Ron', 34, 'emailron.com');");
            statement.addBatch("INSERT INTO users (name, age, email) VALUES ('Nik', 40, 'emailnik.com');");
            statement.addBatch("INSERT INTO users (name, age, email) VALUES ('Ed', 19, 'emailed.com');");
            statement.executeBatch(); //execute пакета запросов
            statement.clearBatch();//очистить пакет запросов

            System.out.println(statement.isClosed()); //boolean закрыт ли statement
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}