package DAO;
/**
 * This is the class to hold the contact Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Contact;

public class contactDao implements DataAccess{

    private ObservableList<Contact> contactList;

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
