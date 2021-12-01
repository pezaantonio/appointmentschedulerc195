package DAO;
/**
 * This is the class to hold the Customer Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao implements DataAccess{

    private ComboBox<String> CustomerDivisionComboBox;

    /**
     * Method to return all customers to an observable list
     * @return ObservableList
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        CustomerDao cDao = new CustomerDao();

        return cDao.getAll();
    }

    @Override
    public ObservableList<Customer> getAll() throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM customers";
        PreparedStatement statement = DatabaseConnection.connection.prepareStatement(sqlQuery);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Customer customer = new Customer(
            result.getInt("Customer_ID"),
            result.getString("Customer_Name"),
            result.getString("Address"),
            result.getString("Postal_Code"),
            result.getString("Phone"),
            result.getTimestamp("Create_Date").toLocalDateTime(),
            result.getString("Created_By"),
            result.getTimestamp("Last_Update").toLocalDateTime(),
            result.getString("Last_Updated_By"),
            result.getInt("Division_Id")
            );

            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean update(int id, Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    /**
     * Sets the combo box
     * @throws SQLException
     */
    public static ObservableList<String> setCustomerCountryComboBox() throws SQLException {
        ObservableList<String> countryComboBox = FXCollections.observableArrayList();
        String countrySQL = "SELECT division FROM first_level_divisions";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(countrySQL);
        ResultSet result = ps.executeQuery();

        while (result.next()){
            String comboDivisions = result.getString("division");
            countryComboBox.add(comboDivisions);
        }
        ps.close();
        result.close();
        return countryComboBox;
    }
}
