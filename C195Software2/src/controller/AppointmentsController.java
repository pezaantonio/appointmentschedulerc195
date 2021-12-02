package controller;

import DAO.AppointmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    public Button AppointmentsBackButton;
    @FXML
    private TableView<Appointment> AppointmentTableView;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
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

    private boolean isWeekly;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        AppointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentTitle"));
        AppointmentDescColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentDescription"));
        AppointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentLocation"));
        AppointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentContactId"));
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

        isWeekly = true;
}

    /**
     * Send user to the add new appointments form
     * @param actionEvent
     */
    public void toInsertAppointments(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/insertappointments.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Send user back to main menu
     * @param actionEvent
     * @throws IOException
     */
    public void toUsermain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/usermain.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }
}
