package DAO;

import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

public class firstLevelDivisionDao implements DataAccess{

    private ObservableList<FirstLevelDivisions> firstLevelDivisionsList;

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
