package controller;

import DAO.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivisions;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class InsertCustomerController implements Initializable {
    @FXML
    private TextField CustomerNameTextField;
    @FXML
    private TextField CustomerAddressTextField;
    @FXML
    private TextField CustomerPostalCodeTextField;
    @FXML
    private TextField CustomerPhoneNumberTextField;
    @FXML
    private ComboBox<FirstLevelDivisions> CustomerDivisionComboBox;
    @FXML
    private ComboBox<Country> CustomerCountryComboBox;
    @FXML
    protected int prevCustomerId;
    @FXML
    protected int nextCustomerId;

    private Customer customer;

    private boolean isValid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCountryComboBox.setItems(new CountryDao().getCountryList());
    }

    /**
     * Method creates a new customer object and adds to database
     * @return customer
     * @throws SQLException
     */
    public Customer insertCustomer(ActionEvent actionEvent) throws SQLException {
        try {
            customer = new Customer(
                    0,
                    CustomerNameTextField.getText(),
                    CustomerAddressTextField.getText(),
                    CustomerPostalCodeTextField.getText(),
                    CustomerPhoneNumberTextField.getText(),
                    LocalDateTime.now(),
                    UserDao.getUserName(),
                    LocalDateTime.now(),
                    UserDao.getUserName(),
                    CustomerDivisionComboBox.getValue().getDivisionID()
            );
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("One or more entries are empty. Please submit an entry for each field");
            Optional<ButtonType> result = alert.showAndWait();
        }


        CustomerDao addCustomer = new CustomerDao();

        if(addCustomer.insert(customer) && isValidCustomer()){
            saveRedirect(actionEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("One or more entries are empty. Please submit an entry for each field");
            Optional<ButtonType> result = alert.showAndWait();
        }
        return customer;
    }

    /**
     * Sends user to the main customer menu
     * @param actionEvent
     * @throws IOException
     */
    public void toCustomerMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to populate Division combo box based on country selected
     * @param actionEvent
     */
    public void onCountrySelect(ActionEvent actionEvent) {
        CustomerDivisionComboBox.setItems(FirstLevelDivisionDao.getCountryDivision(CustomerCountryComboBox.getSelectionModel().getSelectedItem().getCountryID()));
    }

    /**
     * Method will determine if all entries are filled before sending to database
     * @return boolean isValid
     */
    public boolean isValidCustomer(){
        isValid = true;

        if(CustomerNameTextField.getText().isEmpty()){
            isValid = false;
        }
        if(CustomerAddressTextField.getText().isEmpty()){
            isValid = false;
        }
        if(CustomerPostalCodeTextField.getText().isEmpty()){
            isValid = false;
        }
        if(CustomerPhoneNumberTextField.getText().isEmpty()){
            isValid = false;
        }
        if(CustomerCountryComboBox.getValue() == null){
            isValid = false;
        }
        if(CustomerDivisionComboBox.getValue() == null){
            isValid = false;
        }
        return isValid;
    }

    /**
     * Method to redirect user to main menu after successful save
     * @param actionEvent
     */
    public void saveRedirect(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Successful");
        alert.setContentText("Save Successful, returning to customers menu");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("view/customers.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();
            }
        }
    }

