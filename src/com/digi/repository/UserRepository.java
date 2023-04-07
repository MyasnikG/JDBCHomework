package com.digi.repository;

import com.digi.model.User;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {


    public static void addUser(User user) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (?,?,?,?,?,?)");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(User user) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("Select * from users where id=?");
            preparedStatement1.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement1.executeQuery();

            String oldName="";
            String oldSurname = "";
            int oldage = 0;

            while(resultSet.next()) {
                if(user.getId()==resultSet.getInt("id")) {
                    oldName = resultSet.getString("first_name");
                    oldSurname = resultSet.getString("last_name");
                    oldage = resultSet.getInt("age");
                }
            }

            PreparedStatement preparedStatement = connection.prepareStatement("update users set first_name = ?, last_name =?, age = ? where id=?");

            if (user.getName() == null) {
                preparedStatement.setString(1, oldName);
            } else {
                preparedStatement.setString(1, user.getName());
            }

            if (user.getSurname() == null) {
                preparedStatement.setString(2, oldSurname);
            } else {
                preparedStatement.setString(2, user.getSurname());
            }

            if (user.getAge() == 0) {
                preparedStatement.setInt(3, oldage);
            } else {
                preparedStatement.setInt(3, user.getAge());
            }

            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUser(int id) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id =?");
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<User> getAll() {
        Connection connection = MyDataSource.getConnection();
        ArrayList<User> usersList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(id, firstName, lastName, age, email, password);
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }
}
