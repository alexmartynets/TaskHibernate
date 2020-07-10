package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)");
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе createUsersTable: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Выброшено SQL исключение в методе dropUsersTable: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        this.session = Util.getSessionFactory().openSession();
        session.save(new User(name, lastName, age));
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        this.session = Util.getSessionFactory().openSession();
        session.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id);
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> userList;
        this.session = Util.getSessionFactory().openSession();
        userList = (List<User>) session.createQuery("FROM User").list();
        session.close();
        return userList;
    }

    @Override
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
