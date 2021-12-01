package controller;

import DAO.CustomerDao;
import DAO.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private ComboBox<String> CustomerCityComboBox;
    @FXML
    private ComboBox<String> CustomerDivisionComboBox;

    ObservableList<String> countryComboBox = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerCustNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        CustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        CustomerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPostalCode"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        CustomerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerDivisionID"));

        try {
            CustomerTableView.setItems(CustomerDao.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CustomerCustomerIDTextField.setText("Auto-Generated");
        try {
            setCustomerCountryComboBox();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateCustomer(){
        Customer selectedCustomer = CustomerTableView.getSelectionModel().getSelectedItem();

        CustomerCustomerIDTextField.setText(Integer.toString(selectedCustomer.getCustomerID()));
        CustomerCustomerNameTextField.setText(selectedCustomer.getCustomerName());
        CustomerAddressTextField.setText(selectedCustomer.getCustomerAddress());
        CustomerPostalCodeTextField.setText(selectedCustomer.getCustomerPostalCode());
        CustomerPhoneTextField.setText(selectedCustomer.getCustomerPhone());
        //CustomerDivisionComboBox.setValue(selectedCustomer.getCustomerDivisionID());

    }

    public void setCustomerCountryComboBox() throws SQLException {
        String countrySQL = "SELECT division FROM first_level_divisions";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(countrySQL);
        ResultSet result = ps.executeQuery();

        while (result.next()){
            String comboDivisions = result.getString("division");
            countryComboBox.add(comboDivisions);
            CustomerDivisionComboBox.setItems(countryComboBox);
        }
        ps.close();
        result.close();
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
