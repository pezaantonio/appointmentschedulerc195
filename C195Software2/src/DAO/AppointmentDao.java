package DAO;
/**
 * This is the class to hold the Appointment Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import model.Appointment;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
                    result.getString("Title"),
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
    public boolean update(int id, Object o) throws SQLException {
        Appointment appointment = (Appointment) o;

        String updateAppointment = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = NOW(), Last_Updated_By = ?, Customer_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement updateAppointmentSQL = DatabaseConnection.connection.prepareStatement(updateAppointment);

        updateAppointmentSQL.setString(1, appointment.getAppointmentTitle());
        updateAppointmentSQL.setString(2, appointment.getAppointmentDescription());
        updateAppointmentSQL.setString(3, appointment.getAppointmentLocation());
        updateAppointmentSQL.setString(4, appointment.getAppointmentType());
        updateAppointmentSQL.setTimestamp(5, Timestamp.valueOf(appointment.getAppointmentStart()));
        updateAppointmentSQL.setTimestamp(6, Timestamp.valueOf(appointment.getAppointmentEnd()));
        updateAppointmentSQL.setString(7, UserDao.getUserName());
        updateAppointmentSQL.setInt(8, appointment.getAppointmentCustId());
        updateAppointmentSQL.setInt(9, appointment.getAppointmentContactId());

        updateAppointmentSQL.setInt(10, appointment.getAppointmentID());

        if (updateAppointmentSQL.executeUpdate() > 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean insert(Object o) throws SQLException{
        Appointment appointment = (Appointment) o;
        boolean validInsert = false;
        //todo insert into tablename (list of columns) values(values in order of columns)
        String insertAppointment = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,NOW(),?,NOW(),?,?,?,?)";
        PreparedStatement insertAppointmentSQL = DatabaseConnection.connection.prepareStatement(insertAppointment);

        insertAppointmentSQL.setString(1, appointment.getAppointmentTitle());
        insertAppointmentSQL.setString(2, appointment.getAppointmentDescription());
        insertAppointmentSQL.setString(3,appointment.getAppointmentLocation());
        insertAppointmentSQL.setString(4, appointment.getAppointmentType());
        insertAppointmentSQL.setTimestamp(5, Timestamp.valueOf(appointment.getAppointmentStart()));
        insertAppointmentSQL.setTimestamp(6, Timestamp.valueOf(appointment.getAppointmentEnd()));
        insertAppointmentSQL.setString(7, appointment.getAppointmentCreatedBy());
        insertAppointmentSQL.setString(8, appointment.getAppointmentUpdatedBy());
        insertAppointmentSQL.setInt(9, appointment.getAppointmentCustId());
        insertAppointmentSQL.setInt(10, appointment.getAppointmentUserId());
        insertAppointmentSQL.setInt(11, appointment.getAppointmentContactId());

        if (insertAppointmentSQL.executeUpdate() > 0){
            validInsert = true;
        }
        insertAppointmentSQL.close();

        // date and time for create_Date and last_updated use now()
        // executeUpdate or executeQuery (look for examples on PreparedStatement)


        return validInsert;
    }

    @Override
    public boolean delete(int id) throws SQLException{
        String deleteAppointment = "DELETE FROM appointments WHERE Appointment_ID =?";
        PreparedStatement deleteAppointmentSQL = DatabaseConnection.connection.prepareStatement(deleteAppointment);

        deleteAppointmentSQL.setInt(1, id);

        if(deleteAppointmentSQL.executeUpdate() > 0){
            return true;
        }

        return false;
    }

    public ObservableList<Appointment> appointmentsByWeek(){
        ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();


        return weekAppointments;
    }


    public static Appointment getUpcomingAppointment() throws SQLException {
        String appointmentCheck = "SELECT * FROM appointments WHERE Start > current_date() AND User_ID = " + UserDao.getUserId();
        PreparedStatement appointmentCheckSQL= DatabaseConnection.connection.prepareStatement(appointmentCheck);
        ResultSet result = appointmentCheckSQL.executeQuery();

        result.next();
        Appointment upcomingAppointment = new Appointment(
                result.getInt("Appointment_ID"),
                result.getString("Title"),
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
        return upcomingAppointment;
    }
}

