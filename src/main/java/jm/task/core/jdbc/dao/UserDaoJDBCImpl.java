package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)");
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе createUsersTable: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе dropUsersTable: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе saveUser: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе removeUserById: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                userList.add(new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе getAllUsers: " + e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе cleanUsersTable: " + e.getMessage());
        }
    }
}
