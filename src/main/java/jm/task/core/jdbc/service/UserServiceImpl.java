package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

/*
    Комментарий №3: использовать полиморфизм, обращаться к классу через интерфейс

    Как было:
        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
    Должно быть:
        UserDao userDao = new UserDaoJDBCImpl();
    Чтобы это заработало, необходимо было добавить импорт:
        import jm.task.core.jdbc.dao.UserDao;
*/

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
