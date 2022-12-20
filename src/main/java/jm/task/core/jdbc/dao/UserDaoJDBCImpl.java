package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS User (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT, 
                        name VARCHAR(45), 
                        last_name VARCHAR(45), 
                        age INT);
                     """;
        try {
            Util.connection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table created");
    }

    public void dropUsersTable() {
        String sql = """
                     DROP TABLE IF EXISTS User;
                     """;
        try {
            Util.connection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table dropped");
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util.connection().createStatement().execute("INSERT INTO User(name, last_Name, age) VALUES('" + name + "', '" + lastName + "', '" + age + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User " + name + " added in database");
    }

    public void removeUserById(long id) {
        try {
            Util.connection().createStatement().execute("DELETE FROM User WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User with id " + id + " deleted");
    }

    public List<User> getAllUsers() {
        String sql = """
                     SELECT * From User;
                     """;
        List<User> people = new ArrayList<>();
        ResultSet res = null;
        try {
            res = Util.connection().createStatement().executeQuery(sql);
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong(1));
                user.setName(res.getString(2));
                user.setLastName(res.getString(3));
                user.setAge(res.getByte(4));
                people.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Userlist created");
        return people;
    }

    public void cleanUsersTable() {
        String sql = """
                     TRUNCATE User;
                     """;
        try{
            Util.connection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table clear");
    }
}
