package controller;

import DAO.CustomerDao;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private TableColumn<Customer, String> CustomerCreatedColumn;
    @FXML
    private TableColumn<Customer, String> CustomerLastUpdateColumn;
    @FXML
    private TableColumn<Customer, String> CustomerLastUpdatedByColumn;
    @FXML
    private TableColumn<Customer, String> CustomerCountryIdColumn;
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
    private RadioButton CustomerActiveRadioButton;
    @FXML
    private RadioButton CustomerInactiveRadioButton;
    @FXML
    private ToggleGroup RadioButtonToggleGroup;
    @FXML
    private Button CustomerBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerCustNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        CustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        CustomerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPostalCode"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        CustomerLastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerLastUpdate"));
        CustomerCountryIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerCountryIdColumn"));

        try {
            CustomerTableView.setItems(CustomerDao.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CustomerCustomerIDTextField.setText("Auto-Generated");
    }

    public void updateCustomer(){
        Customer selectedCustomer = CustomerTableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedCustomer);

        CustomerCustomerIDTextField.setText(Integer.toString(selectedCustomer.getInt("customerId")));
        CustomerCustomerNameTextField.setText(selectedCustomer.getString("customerName"));
        CustomerAddressTextField.setText(selectedCustomer.getString("address"));
        CustomerAddress2TextField.setText(selectedCustomer.getString("address2"));
        CustomerCityComboBox.setValue(selectedCustomer.getString("city"));
        CustomerCountryComboBox.setValue(selectedCustomer.getString("country"));
        CustomerPostalCodeTextField.setText(selectedCustomer.getString("postalCode"));
        CustomerPhoneTextField.setText(selectedCustomer.getString("phone"));

    }

    @FXML
    private void CustomerCustomerIDTextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerCustomerNameTextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerAddressTextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerAddress2TextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerCityComboBoxHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerCountryComboBoxHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerPostalCodeTextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void CustomerPhoneTextFieldHandler(ActionEvent event) {
    }

    public void toUsermain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/usermain.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }
}
