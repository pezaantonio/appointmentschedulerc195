package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentTableView;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIdColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTitleColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentDescColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentLocationColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentContactColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTypeColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentStartColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentEndColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentCustIdColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentUserIdColumn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*PropertyValueFactory<Appointment, String> appointmentId = new PropertyValueFactory<>("AppointmentId");
        PropertyValueFactory<Appointment, String> appointmentTitle = new PropertyValueFactory<>("AppointmentTitle");
        PropertyValueFactory<Appointment, String> appointmentDescription = new PropertyValueFactory<>("AppointmentDescription");
        PropertyValueFactory<Appointment, String> appointmentLocation = new PropertyValueFactory<>("AppointmentLocation");
        PropertyValueFactory<Appointment, String> appointmentContact = new PropertyValueFactory<>("AppointmentContact");
        PropertyValueFactory<Appointment, String> appointmentType = new PropertyValueFactory<>("AppointmentType");
        PropertyValueFactory<Appointment, String> appointmentStart = new PropertyValueFactory<>("AppointmentStart");
        PropertyValueFactory<Appointment, String> appointmentEnd = new PropertyValueFactory<>("AppointmentEnd");
        PropertyValueFactory<Appointment, String> appointmentCustId = new PropertyValueFactory<>("AppointmentCustId");
        PropertyValueFactory<Appointment, String> appointmentUserId = new PropertyValueFactory<>("AppointmentUserId");*/

        AppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        AppointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentTitle"));
        AppointmentDescColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentDescription"));
        AppointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentLocation"));
        AppointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentContact"));
        AppointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
        AppointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentStart"));
        AppointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentEnd"));
        AppointmentCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentCustId"));
        AppointmentUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentUserId"));

    }
}
