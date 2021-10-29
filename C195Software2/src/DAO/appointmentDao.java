package DAO;
/**
 * This is the class to hold the Appointment Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Appointment;

public class appointmentDao implements DataAccess{

    private ObservableList<Appointment> appointmentList;

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
