package DAO;
/**
 * This is the class to hold the Appointment Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.ObservableList;
import model.Appointment;

public class AppointmentDao implements DataAccess{

    private ObservableList<Appointment> appointmentList;

    @Override
    public ObservableList<Appointment> getAll() {
        return null;
    }

    @Override
    public boolean insert(Object o) {
        boolean validInsert = false;
        //todo insert into tablename (list of columns) values(values in order of columns)
        // date and time for create_Date and last_updated use now()
        // executeUpdate or executeQuery (look for examples on PreparedStatement)
        return validInsert;
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
