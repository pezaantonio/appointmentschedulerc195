package DAO;
/**
 * This is the class to hold the Data Class interface
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DataAccess<T> {

    public ObservableList<T> getAll() throws SQLException;

    public boolean insert(T t);

    public boolean update(int id, T t);

    public boolean delete(int id);

}
