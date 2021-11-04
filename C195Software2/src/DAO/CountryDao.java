package DAO;
/**
 * This is the class to hold the country Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Country;

public class CountryDao implements DataAccess{

    private ObservableList<Country> countryList;

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
