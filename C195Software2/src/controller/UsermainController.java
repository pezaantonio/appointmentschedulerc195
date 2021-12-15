package controller;

import DAO.AppointmentDao;
import DAO.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Appointment;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Lambda alert used to alert the user if an appointment is coming up
 */
interface LambdaAlert{
    String alert();
}

public class UsermainController implements Initializable {

    @FXML
    private Label appointmentAlertLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LambdaAlert alert = () -> "You have an appointment coming up in the next 15 minutes \n";

        try {
            if (Appointment.appointmentTimeCheck()) {
                appointmentAlertLabel.setText(alert.alert() + AppointmentDao.getUpcomingAppointment().getAppointmentID() + " : " + AppointmentDao.getUpcomingAppointment().getAppointmentTitle());
            } else {
                appointmentAlertLabel.setText("No appointments coming up");
            }
        } catch (NullPointerException | SQLException e) {
            appointmentAlertLabel.setText("No appointments coming up");
        }
    }

    @FXML
    /**
     * Method to handle the customers button on the main screen. Will send user to Customers page
     */
    private void toCustomers(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to send user to appointments page
     * @param actionEvent
     * @throws IOException
     */
    public void toAppointments(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/appointments.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    public void toReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/reports.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    public void onExitMain(ActionEvent actionEvent){
        System.exit(0);
    }

}
