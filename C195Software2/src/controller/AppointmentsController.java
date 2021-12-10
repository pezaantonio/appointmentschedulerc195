package controller;

import DAO.AppointmentDao;
import DAO.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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

    private static Appointment appointmentToUpdate;

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
}

    public void onDeleteAppointment(ActionEvent actionEvent) throws IOException {
        Appointment selectedAppointment = AppointmentTableView.getSelectionModel().getSelectedItem();
        int selectedAppointmentId = selectedAppointment.getAppointmentID();

        if(selectedAppointment == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select an Appointment to delete");
            Optional<ButtonType> result = alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete the selected Appointment?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentDao deleteAppointment = new AppointmentDao();
                try {
                    deleteAppointment.delete(selectedAppointmentId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    AppointmentTableView.setItems(AppointmentDao.getAllAppointments());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
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

    /**
     * Grab selected appointment and send it to update appointment
     * @param actionEvent
     * @throws IOException
     */
    public void toUpdateAppointment(ActionEvent actionEvent) throws IOException {

        appointmentToUpdate = AppointmentTableView.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/updateappointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Update Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * this method returns the appointment to update
     * @return appointmentToUpdate
     */
    public static Appointment getAppointmentToUpdate(){
        return appointmentToUpdate;
    }

}
