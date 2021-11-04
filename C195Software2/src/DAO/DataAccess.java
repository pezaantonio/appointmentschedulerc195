package DAO;
/**
 * This is the class to hold the Data Class interface
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;

import java.sql.SQLException;

public abstract interface DataAccess<T> {

    ObservableList<T> getAll() throws SQLException;

    boolean insert(T t);

    boolean update(int id, T t);

    boolean delete(int id);

}
