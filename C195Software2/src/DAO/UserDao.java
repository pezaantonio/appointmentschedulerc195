package DAO;
/**
 * This is the class to hold the User Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements DataAccess{

    private static int userId;
    private static String userName;


    /**
     * Returns user Id
     * @return  userId
     * */
    public static int getUserId() {
        return userId;
    }

    /**
     * Sets user Id
     * @param userId
    * */
    public static void setUserId(int userId) {
        UserDao.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserDao.userName = userName;
    }

    //todo getter and setter for userID and UserName

    @Override
    /**
     * Method to return all users
     * @return ObservableList<User> userList
     */
    public ObservableList<User> getAll() throws SQLException{
        ObservableList<User> userList = FXCollections.observableArrayList();
        userList.clear();
        String userQuery = "SELECT * FROM users";
        PreparedStatement userSQL = DatabaseConnection.connection.prepareStatement(userQuery);
        ResultSet result = userSQL.executeQuery();

        while (result.next()) {
            int userID = result.getInt("User_ID");
            String userName = result.getString("User_Name");

            userList.add(new User(userID, userName));
        }
        return userList;
    }


    public static ObservableList<User> getAllUsers() throws SQLException {
        UserDao uDao = new UserDao();

        return uDao.getAll();
    }

    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean update(int id, Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    /**
     * Method to check if username and password match
     *
     * @param username
     * @param password
     * @return boolean validUser
     */
    public static boolean checkUser(String username, String password) throws SQLException {
        boolean validUser = false;
        String sqlQuery = "SELECT User_Name, User_ID FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement statement = DatabaseConnection.connection.prepareStatement(sqlQuery);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        while(result.next()){
            userId = result.getInt("User_ID");
            userName = result.getString("User_Name");
            validUser = true;
            break;
        }
        return validUser;
    }
}
