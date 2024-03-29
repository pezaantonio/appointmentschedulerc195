package DAO;
/**
 * This is the class to hold the Customer Data Access Object
 * @author Antonio Peza
 *
 * */

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * method to handle all customer related database queries
 */
public class CustomerDao implements DataAccess{

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

    /**
     * Method to return all customers in an array
     * @return ObservableList Customer
     * @throws SQLException
     */
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

    /**
     * Method to
     * @return
     * @throws SQLException
     */
    @Override
    public boolean insert(Object o) throws SQLException {
        Customer customer = (Customer) o;
        String insertCustomer = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,NOW(),?,NOW(),?,?)";
        PreparedStatement insertCustomerSQL = DatabaseConnection.connection.prepareStatement(insertCustomer);

        //insertCustomerSQL.setInt(1, customer.getCustomerID());
        insertCustomerSQL.setString(1, customer.getCustomerName());
        insertCustomerSQL.setString(2, customer.getCustomerAddress());
        insertCustomerSQL.setString(3, customer.getCustomerPostalCode());
        insertCustomerSQL.setString(4, customer.getCustomerPhone());
        insertCustomerSQL.setString(5, customer.getCustomerCreatedBy());
        insertCustomerSQL.setString(6, customer.getCustomerLastUpdatedBy());
        insertCustomerSQL.setInt(7, customer.getCustomerDivisionID());

        if(insertCustomerSQL.executeUpdate() > 0){
            return true;
        }
        insertCustomerSQL.close();

        return false;
    }

    /**
     * Method to handle all customer update queries
     * @param id
     * @param o
     * @return true
     * @throws SQLException
     */
    @Override
    public boolean update(int id, Object o) throws SQLException{
        Customer customer = (Customer) o;
        String updateCustomer = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement updateCustomerSQL = DatabaseConnection.connection.prepareStatement(updateCustomer);

        updateCustomerSQL.setString(1, customer.getCustomerName());
        updateCustomerSQL.setString(2, customer.getCustomerAddress());
        updateCustomerSQL.setString(3, customer.getCustomerPostalCode());
        updateCustomerSQL.setString(4, customer.getCustomerPhone());
        //updateCustomerSQL.setString(5, customer.getCustomerCreatedBy());
        updateCustomerSQL.setString(5, customer.getCustomerLastUpdatedBy());
        updateCustomerSQL.setInt(6, customer.getCustomerDivisionID());

        updateCustomerSQL.setInt(7, id);

        if(updateCustomerSQL.executeUpdate() > 0){
            return true;
        }
        updateCustomerSQL.close();
        return false;
    }

    /**
     * Method to handle the delete queries of customers
     * @param id
     * @return true
     * @throws SQLException
     */
    @Override
    public boolean delete(int id) throws SQLException{
        String deleteCustomer = "DELETE FROM customers WHERE CUSTOMER_ID =?";
        PreparedStatement deleteCustomerSQL = DatabaseConnection.connection.prepareStatement(deleteCustomer);

        deleteCustomerSQL.setInt(1, id);

        if(deleteCustomerSQL.executeUpdate() > 0){
            return true;
        }

        return false;
    }

}
