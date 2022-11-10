package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();



    public UserDaoJDBCImpl() {
    }

    public void createUsersTable()  {

            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE if not exists users (\n" +
                        "  id int NOT NULL AUTO_INCREMENT,\n" +
                        "  name varchar(45) NOT NULL,\n" +
                        "  lastName varchar(45) NOT NULL,\n" +
                        "  age int NOT NULL,\n" +
                        "  PRIMARY KEY (id)\n" +
                        ") ");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void dropUsersTable()  {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table if exists users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {


        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name, lastName, age) values( ?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id)  {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=? ")) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers()  {
        List<User> userList = new ArrayList<>();

        try(Statement statement =connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

        public void cleanUsersTable ()  {
            try (Statement statement = connection.createStatement()) {

                statement.execute("truncate  users");

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}
