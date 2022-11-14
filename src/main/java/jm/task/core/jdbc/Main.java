package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        Util util = new Util();
        Util.getSessionFactory();
        user.createUsersTable();
        user.saveUser("Анатолий", "Хитров", (byte) 27);
        user.saveUser("Александр", "Русских", (byte) 41);
        user.saveUser("Игорь", "Рыжаков", (byte) 31);
        user.saveUser("Сергей", "Роготин", (byte) 45);
        user.removeUserById(4);
        for (User allUser : user.getAllUsers()) {
            System.out.println(allUser);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
