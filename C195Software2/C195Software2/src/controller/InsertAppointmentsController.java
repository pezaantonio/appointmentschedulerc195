package controller;

import DAO.AppointmentDao;
import DAO.ContactDao;
import DAO.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class InsertAppointmentsController implements Initializable {

    @FXML
    private TextField AppointmentTitleTextField;
    @FXML
    private TextField AppointmentDescriptionTextField;
    @FXML
    private TextField AppointmentLocationTextField;
    @FXML
    private TextField AppointmentTypeTextField;
    @FXML
    private DatePicker AppointmentDatePicker;
    @FXML
    private ComboBox AppointmentStartComboBox;
    @FXML
    private ComboBox AppointmentEndComboBox;
    @FXML
    private TextField AppointmentCustomerIdTextField;
    @FXML
    private TextField AppointmentUserIdTextField;
    @FXML
    private ComboBox AppointmentContactComboBox;

    protected int prevAppointmentId;
    protected int nextAppointmentId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentContactComboBox.setItems(new ContactDao().getAll());

    }
    /**
     * Method to send user to appointments page
     * @param actionEvent
     * @throws IOException
     */
    public void toAppointments(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method grabs the information from text fields and creates an Appointment object with it that will be sent to DB
     * @return
     * @throws SQLException
     */
    public Appointment insertAppointment() throws SQLException {
        Appointment appointment = new Appointment(
                newAppointmentId(),
                AppointmentTitleTextField.getText(),
                AppointmentDescriptionTextField.getText(),
                AppointmentLocationTextField.getText(),
                AppointmentTypeTextField.getText(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                //AppointmentStartComboBox.getValue(),
                //AppointmentEndComboBox.getValue(),
                LocalDateTime.now(),
                "",
                LocalDateTime.now(),
                "",
                Integer.parseInt(AppointmentCustomerIdTextField.getText()),
                Integer.parseInt(AppointmentUserIdTextField.getText()),
                1
                //Integer.parseInt(AppointmentContactComboBox.getValue())
        );

        return appointment;
    }

    /**
     * Method grabs last Appointment ID and increases it by 1
     * @return
     * @throws SQLException
     */
    public int newAppointmentId() throws SQLException {
        String appointmentIdSQL = "select Appointment_ID from appointments order by Appointment_ID desc limit 1";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(appointmentIdSQL);
        ResultSet result = ps.executeQuery();

        while(result.next()){
            prevAppointmentId = result.getInt("Customer_ID");
            nextAppointmentId = prevAppointmentId + 1;
        }
        ps.close();
        result.close();
        return nextAppointmentId;
    }

}
