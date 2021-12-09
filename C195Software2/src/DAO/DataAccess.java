package DAO;
/**
 * This is the class to hold the Data Class interface
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.SQLException;

public interface DataAccess<T> {

    public ObservableList<T> getAll() throws SQLException;

    public boolean insert(T t) throws SQLException;

    public boolean update(int id, T t) throws SQLException;

    public boolean delete(int id) throws SQLException;

}
