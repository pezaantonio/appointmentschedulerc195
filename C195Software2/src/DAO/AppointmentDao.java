package DAO;
/**
 * This is the class to hold the Appointment Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDao implements DataAccess{

    @Override
    public ObservableList<Appointment> getAll() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM appointments";
        PreparedStatement statement = DatabaseConnection.connection.prepareStatement(sqlQuery);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Appointment appointment = new Appointment(
                    result.getInt("Appointment_ID"),
                    result.getString("title"),
                    result.getString("Description"),
                    result.getString("Location"),
                    result.getString("Type"),
                    result.getTimestamp("Start").toLocalDateTime(),
                    result.getTimestamp("End").toLocalDateTime(),
                    result.getTimestamp("Create_Date").toLocalDateTime(),
                    result.getString("Created_By"),
                    result.getTimestamp("Last_Update").toLocalDateTime(),
                    result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"),
                    result.getInt("User_ID"),
                    result.getInt("Contact_ID")
            );

            appointmentList.add(appointment);
        }

        return appointmentList;
    }

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        AppointmentDao aDao = new AppointmentDao();

        return aDao.getAll();
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
