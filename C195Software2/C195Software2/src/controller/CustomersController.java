package controller;

import DAO.CountryDao;
import DAO.CustomerDao;
import DAO.DatabaseConnection;
import DAO.FirstLevelDivisionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Country;
import model.Customer;
import model.FirstLevelDivisions;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @FXML
    protected TableView<Customer> CustomerTableView;
    @FXML
    private TableColumn<Customer, Integer> CustomerCustIdColumn;
    @FXML
    private TableColumn<Customer, String> CustomerCustNameColumn;
    @FXML
    private TableColumn<Customer, String> CustomerAddressColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPostalCodeColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPhoneColumn;
    @FXML
    private TableColumn<Customer, String> CustomerCountryColumn;
    @FXML
    private TableColumn<Customer, String> CustomerLastUpdateColumn;
    @FXML
    private TableColumn<Customer, String> CustomerLastUpdatedByColumn;
    @FXML
    private TableColumn<Customer, String> CustomerDivisionColumn;
    @FXML
    private TextField CustomerCustomerNameTextField;
    @FXML
    private TextField CustomerCustomerIDTextField;
    @FXML
    private TextField CustomerAddressTextField;
    @FXML
    private TextField CustomerAddress2TextField;
    @FXML
    private TextField CustomerPostalCodeTextField;
    @FXML
    private TextField CustomerPhoneTextField;
    @FXML
    private ComboBox<FirstLevelDivisions> CustomerDivisionComboBox;
    @FXML
    private ComboBox<Country> CustomerCountryComboBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerCustNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        CustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        CustomerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPostalCode"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        CustomerCountryColumn.setCellValueFactory(new PropertyValueFactory<>("CountryName"));
        CustomerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("DivisionName"));

        try {
            CustomerTableView.setItems(CustomerDao.getAllCustomers());
            CustomerCountryComboBox.setItems(new CountryDao().getCountryList());
            if(CustomerCountryComboBox.getValue() != null) {

                CustomerDivisionComboBox.setItems(new

                        FirstLevelDivisionDao().getCountryDivision(CustomerCountryComboBox.getSelectionModel().getSelectedItem().getCountryID()));
            }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CustomerCustomerIDTextField.setText("Auto-Generated");
    }

    /**
     * This controls the button that will auto populate a form with selected form
     */
    public void updateCustomer(){
        Customer selectedCustomer = CustomerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a customer to update");
            Optional<ButtonType> result=alert.showAndWait();
        } else {
            CustomerCustomerIDTextField.setText(Integer.toString(selectedCustomer.getCustomerID()));
            CustomerCustomerNameTextField.setText(selectedCustomer.getCustomerName());
            CustomerAddressTextField.setText(selectedCustomer.getCustomerAddress());
            CustomerPostalCodeTextField.setText(selectedCustomer.getCustomerPostalCode());
            CustomerPhoneTextField.setText(selectedCustomer.getCustomerPhone());
            CustomerCountryComboBox.setValue(selectedCustomer.getCustomerCountry());
            CustomerDivisionComboBox.setValue(selectedCustomer.getFirstLevelDivision());
            }
        }

    /**
     * sends user to insert customer page
     * @param actionEvent
     * @throws IOException
     */
    public void toInsertCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/insertcustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sends user to the main menu
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