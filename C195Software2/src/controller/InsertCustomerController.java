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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCountryComboBox.setItems(new CountryDao().getCountryList());
        CustomerDivisionComboBox.setItems(new FirstLevelDivisionDao().getCountryDivision(1));
    }

    public void insertCustomer(Customer customer) throws SQLException {
        customer = new Customer(
                newCustomerId(),
                CustomerNameTextField.getText(),
                CustomerAddressTextField.getText(),
                CustomerPostalCodeTextField.getText(),
                CustomerPhoneNumberTextField.getText(),
                LocalDateTime.now(),
                "",
                LocalDateTime.now(),
                "",
                CustomerDivisionComboBox.getValue().getDivisionID()
        );
        

        CustomerDao addCustomer = new CustomerDao();
        addCustomer.insert(customer);

    }

    public void toCustomerMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }


    public int newCustomerId() throws SQLException {
        String customerIdSQL = "select Customer_ID from customers order by Customer_ID desc limit 1";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(customerIdSQL);
        ResultSet result = ps.executeQuery();

        while(result.next()){
            prevCustomerId = result.getInt("Customer_ID");
            nextCustomerId = prevCustomerId + 1;
        }
        ps.close();
        result.close();
        return nextCustomerId;
    };
}
