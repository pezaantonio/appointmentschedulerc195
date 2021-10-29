package DAO;
/**
 * This is the class to hold the User Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.User;

public class userDao implements DataAccess{

    private ObservableList<User> userList;

    @Override
    public ObservableList getAll() {
        return null;
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
}
