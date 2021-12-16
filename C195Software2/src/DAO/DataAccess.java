package DAO;
/**
 * This is the class to hold the Data Class interface
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.SQLException;

/**
 * Interface for DataAccess
 * @param <T>
 */
public interface DataAccess<T> {

    /**
     * Will return all items in a database
     * @return
     * @throws SQLException
     */
    public ObservableList<T> getAll() throws SQLException;

    /**
     * Will insert a new item in the database
     * @param t
     * @return
     * @throws SQLException
     */
    public boolean insert(T t) throws SQLException;

    /**
     * Will update a selected item in the database
     * @param id
     * @param t
     * @return
     * @throws SQLException
     */
    public boolean update(int id, T t) throws SQLException;

    /**
     * Will delte a selected item in a database
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean delete(int id) throws SQLException;

}
