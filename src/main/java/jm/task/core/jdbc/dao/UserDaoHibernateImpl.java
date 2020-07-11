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
        try {
            session = Util.getSessionFactory().openSession();
            session.createSQLQuery("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)").executeUpdate();
            session.close();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе createUsersTable: " + e.getMessage());
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.close();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе dropUsersTable: " + e.getMessage());
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        this.session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        this.session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
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
        session = Util.getSessionFactory().openSession();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.close();
    }
}