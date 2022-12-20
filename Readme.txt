Комментарий от ментора №1 - Методы создания и удаления таблицы пользователей
	в классе UserHibernateDaoImpl должны быть реализованы
	с помощью SQL. - остальные с помошью HQL

Что сделано:
В методе getAllUsers()
    строка          userList = session.createSQLQuery("FROM User;").getResultList();
    заменена на     userList = session.createQuery("FROM User").getResultList();
В методе cleanUsersTable()
    строка          session.createSQLQuery("TRUNCATE TABLE User;").executeUpdate();
    заменена на     session.createQuery("DELETE FROM User");



Комментарий от ментора №2: в репозитории не должно быть внутренних и сгенерированных 
	директорий - добавь гитигнор и удали из репо.
	работать в данном репозитории - новый не создавать!



Комментарий от ментора №3: использовать полиморфизм, обращаться к классу через интерфейс

Здесь, полагаю, речь о классе UserService
Как было:
    UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
Что сделано:
    UserDao userDao = new UserDaoJDBCImpl();
Чтобы это заработало, необходимо было также добавить импорт:
    import jm.task.core.jdbc.dao.UserDao;



Комментарий от ментора №4: поля класса не приватные



Комментарий от ментора №5: параметризуй квери - не работай с сырым типом

Здесь, полагаю, речь о классе UserDaoHibernate
Что сделано:
В методах заведена переменная String. В неё перенесено значение для Query.