package DAO;
/**
 * This is the class to hold the Customer Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Customer;

public class customerDao implements DataAccess{

    private ObservableList<Customer> customerList;

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
