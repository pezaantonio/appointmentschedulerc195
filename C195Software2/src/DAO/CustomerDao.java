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

    protected int prevCustomerId;
    protected int nextCustomerId;

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
            customer.getCountryName();
            customer.getDivisionName();
            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public boolean insert(Customer customer) {

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

}
