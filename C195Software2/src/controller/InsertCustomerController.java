package controller;

import DAO.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsertCustomerController implements Initializable {
    @FXML
    private ComboBox<String> CustomerDivisionComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CustomerDivisionComboBox.setItems(CustomerDao.setCustomerCountryComboBox());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void toCustomerMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }
}
