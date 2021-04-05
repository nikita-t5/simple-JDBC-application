import java.sql.*;

public class Main {

    public static void main(String[] args) {
        DBWorker worker = new DBWorker(); //создание connection
        try (Statement statement = worker.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users"); //resultSet - полная модель всей таблицы
            while (resultSet.next()) {  //Перебор строк с данными. Изначально курсор на первой позиции. next() - сместсить курсор на одну строку
                String RSName = resultSet.getString(2); //нумерация для курсора начинается с 1
                int RSAge = resultSet.getInt(3);
                String RSEmail = resultSet.getString("email"); //в аргументе название колонки
                User user = new User(RSName, RSAge, RSEmail);
                System.out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        worker.closeConnection(); //закрытие connection
    }
}