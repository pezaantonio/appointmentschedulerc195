package DAO;
/**
 * This is the class to hold the Data Class interface
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;

public abstract interface DataAccess<T> {

    ObservableList<T> getAll();

    boolean insert(T t);

    boolean update(int id, T t);

    boolean delete(int id);

}
