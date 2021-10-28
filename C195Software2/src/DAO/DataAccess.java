package DAO;

import javafx.collections.ObservableList;

public abstract interface DataAccess<T> {

    ObservableList<T> getAll();

    boolean insert(T t);

    boolean update(int id, T t);

    boolean delete(int id);

}
