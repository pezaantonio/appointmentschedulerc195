package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import controller.LoginController;

public class UpdateAppointmentsController implements Initializable {

    @FXML
    private TextField AppointmentIdTextField;
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
    private ComboBox<LocalDateTime> AppointmentStartComboBox;
    @FXML
    private ComboBox<LocalDateTime> AppointmentEndComboBox;
    @FXML
    private TextField AppointmentCustomerIdTextField;
    @FXML
    private TextField AppointmentUserIdTextField;
    @FXML
    private ComboBox<Contact> AppointmentContactComboBox;
    @FXML
    private ComboBox<Customer> AppointmentCustomerIDComboBox;

    private Appointment appointmentToUpdate;

    protected int prevAppointmentId;
    protected int nextAppointmentId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentToUpdate = AppointmentsController.getAppointmentToUpdate();

        AppointmentIdTextField.setText(Integer.toString(appointmentToUpdate.getAppointmentID()));
        AppointmentTitleTextField.setText(appointmentToUpdate.getAppointmentTitle());
        AppointmentDescriptionTextField.setText(appointmentToUpdate.getAppointmentTitle());
        AppointmentLocationTextField.setText(appointmentToUpdate.getAppointmentLocation());
        AppointmentTypeTextField.setText(appointmentToUpdate.getAppointmentType());
        AppointmentStartComboBox.setValue(appointmentToUpdate.getAppointmentStart());
        AppointmentEndComboBox.setValue(appointmentToUpdate.getAppointmentEnd());
        try {
            AppointmentCustomerIDComboBox.setValue(appointmentToUpdate.getCustomerFromCustomerId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AppointmentUserIdTextField.setText(Integer.toString(appointmentToUpdate.getAppointmentUserId()));
        try {
            AppointmentContactComboBox.setValue(appointmentToUpdate.getContactFromContactId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AppointmentContactComboBox.setItems(new ContactDao().getAll());
        AppointmentStartComboBox.setItems(new DateTime().getStartList());
        try {
            AppointmentCustomerIDComboBox.setItems(new CustomerDao().getAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void onStartTimeSelect(ActionEvent actionEvent){
        AppointmentEndComboBox.setItems(new DateTime().getEndList(AppointmentStartComboBox.getSelectionModel().getSelectedItem()));
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
    public Appointment insertAppointment(ActionEvent actionEvent) throws SQLException {
        Appointment appointment = new Appointment(
                0,
                AppointmentTitleTextField.getText(),
                AppointmentDescriptionTextField.getText(),
                AppointmentLocationTextField.getText(),
                AppointmentTypeTextField.getText(),
                AppointmentStartComboBox.getValue(),
                AppointmentEndComboBox.getValue(),
                LocalDateTime.now(),
                UserDao.getUserName(),
                LocalDateTime.now(),
                UserDao.getUserName(),
                AppointmentCustomerIDComboBox.getValue().getCustomerID(),
                UserDao.getUserId(),
                AppointmentContactComboBox.getValue().getContactID()
        );

        AppointmentDao newAppointment = new AppointmentDao();

        if(newAppointment.insert(appointment)){
            saveRedirect(actionEvent);
        }

        return appointment;
    }

    public void saveRedirect(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Successful");
        alert.setContentText("Save Successful, returning to Appointments menu");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("view/appointments.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }
}