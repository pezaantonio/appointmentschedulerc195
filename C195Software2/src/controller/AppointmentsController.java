package controller;

import DAO.AppointmentDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.net.URL;
import java.sql.SQLException;
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

        try {
            AppointmentTableView.setItems(AppointmentDao.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
