package DAO;
/**
 * This is the class to hold the Customer Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao implements DataAccess{

    public ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Customer> getAll() throws SQLException {
        String sqlQuery = "SELECT * FROM customers";
        PreparedStatement statement = DatabaseConnection.connection.prepareStatement(sqlQuery);

        ResultSet result = statement.executeQuery(sqlQuery);

        Customer customer = new Customer();
        while (result.next()) {
            customer.setCustomerID(result.getInt("Customer_ID"));
            customer.setCustomerName(result.getString("Customer_Name"));
            customer.setCustomerAddress(result.getString("Address"));
            customer.setCustomerZip(result.getString("Postal_Code"));
            customer.setCustomerPhone(result.getString("Phone"));
            customer.setCustomerCreateDate(result.getString("Create_Date"));
            customer.setCustomerCreatedBy(result.getString("Created_By"));
            customer.setCustomerLastUpdate(result.getString("Last_Update"));
            customer.setCustomerLastUpdatedBy(result.getString("Last_Updated_By"));
            customer.setCustomerDivisionID(result.getString("Division_Id"));
        }
        customerList.addAll(customer);
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
}
