import java.sql.*;

public class Main {

    public static void main(String[] args) {
        DBWorker worker = new DBWorker(); //создание connection
        String insert = "INSERT INTO users (name, age, email) VALUES(?,?,?);"; //подготовленное выражение - шаблон запроса. можно исп синтаксис (:1, :2,...)

        try (PreparedStatement preparedStatement = worker.getConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, "Lee"); //выставление 1-го параметра для запроса
            preparedStatement.setInt(2, 78); //выставлениу 2-го параметра для запроса
            preparedStatement.setString(3, "emaillee.com");
            preparedStatement.execute(); //выполнить запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }

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