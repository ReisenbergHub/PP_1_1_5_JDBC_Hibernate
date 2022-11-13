package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
/*
    Комментарий №1 - Методы создания и удаления таблицы пользователей в классе UserHibernateDaoImpl
        должны быть реализованы с помощью SQL. - остальные с помошью HQL
    Что сделано:
    В методе getAllUsers()
        строка          userList = session.createSQLQuery("FROM User;").getResultList();
        заменена на     userList = session.createQuery("FROM User").getResultList();
    В методе cleanUsersTable()
        строка          session.createSQLQuery("TRUNCATE TABLE User;").executeUpdate();
        заменена на     session.createQuery("DELETE FROM User");
*/

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(45), last_name VARCHAR(45), age INT);").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Table created");
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User;").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Table dropped");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("User " + name + " added in database");
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("User with id " + id + " deleted");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (final Session session = sessionFactory.openSession()) {
            userList = session.createQuery("FROM User").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Userlist created");
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Table clear");
    }
}
