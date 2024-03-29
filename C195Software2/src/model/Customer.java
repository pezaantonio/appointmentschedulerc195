package model;
/**
 * Class to hold customers from the database
 */

import DAO.DatabaseConnection;
import DAO.FirstLevelDivisionDao;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * class to hold customers from the database
 */
public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private LocalDateTime customerCreateDate;
    private String customerCreatedBy;
    private LocalDateTime customerLastUpdate;
    private String customerLastUpdatedBy;
    private int customerDivisionID;

    protected String customerDivisionName;
    protected String customerCountryName;
    protected FirstLevelDivisions customerFirstLevelDivision;
    protected Country customerCountry;
    protected int prevCustomerId;
    protected int nextCustomerId;

    /**
     * Constructor for Customer object (fully parameterized)
     *
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param customerCreateDate
     * @param customerCreatedBy
     * @param customerLastUpdate
     * @param customerLastUpdatedBy
     * @param customerDivisionID
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, LocalDateTime customerCreateDate, String customerCreatedBy, LocalDateTime customerLastUpdate, String customerLastUpdatedBy, int customerDivisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerCreateDate = customerCreateDate;
        this.customerCreatedBy = customerCreatedBy;
        this.customerLastUpdate = customerLastUpdate;
        this.customerLastUpdatedBy = customerLastUpdatedBy;
        this.customerDivisionID = customerDivisionID;
    }

    /**
     * returns customer create date
     * @return customerCreateDate
     */
    public LocalDateTime getCustomerCreateDate() {
        return customerCreateDate;
    }

    /**
     * Sets customerCreateDate
     * @param customerCreateDate
     */
    public void setCustomerCreateDate(LocalDateTime customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }

    /**
     * returns customerLastUpdate
     * @return customerLastUpdate
     */
    public LocalDateTime getCustomerLastUpdate() {
        return customerLastUpdate;
    }

    /**
     * sets customerlastupdate
     * @param customerLastUpdate
     */
    public void setCustomerLastUpdate(LocalDateTime customerLastUpdate) {
        this.customerLastUpdate = customerLastUpdate;
    }

    /**
     * returns customerlastupdatedby
     * @return customerLastUpdateBy
     */
    public String getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    /**
     * sets customerLastUpdatedBy
     * @param customerLastUpdatedBy
     */
    public void setCustomerLastUpdatedBy(String customerLastUpdatedBy) {
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    /**
     * return customerDivisionID
     * @return
     */
    public int getCustomerDivisionID() {
        return customerDivisionID;
    }

    /**
     * sets customerDivisionID
     * @param customerDivisionID
     */
    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }

    /**
     * return customerCreatedBy
     * @return customerCreatedBy
     */
    public String getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    /**
     * sets customerCreatedBy
     * @param customerCreatedBy
     */
    public void setCustomerCreatedBy(String customerCreatedBy) {
        this.customerCreatedBy = customerCreatedBy;
    }

    /**
     * Function to set customer ID
     * @param customerID
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**
     * Function to return customer ID
     * @return customerID
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Function to set Customer Name
     * @param customerName
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
     * Function to return customer Name
     * @return customerName
     */
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Function to set customer Address
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress){
        this.customerAddress = customerAddress;
    }

    /**
     * Function to return customer Address
     * @return customerAddress
     */
    public String getCustomerAddress(){
        return customerAddress;
    }

    public void setCustomerPostalCode(String customerPostalCode){
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Function to return customerPostalCode
     * @return customerPostalCode
     */
    public String getCustomerPostalCode(){
        return customerPostalCode;
    }

    /**
     * Function to set customerPhone
     * @param customerPhone
     */
    public void setCustomerPhone(String customerPhone){
        this.customerPhone = customerPhone;
    }

    /**
     * Function to return customerPhone
     * @return customerPhone
     */
    public String getCustomerPhone(){
        return customerPhone;
    }

    /**
     * Method to return customer division name based on division ID
     * @return customerDivisionName
     */
    public String getDivisionName() {
        // todo use division ID to return division name
        try {
            String divisionSQL = "SELECT * FROM first_level_divisions INNER JOIN countries WHERE Division_ID = " + customerDivisionID;
            PreparedStatement ps = DatabaseConnection.connection.prepareStatement(divisionSQL);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                customerDivisionName = result.getString("Division");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerDivisionName;
    }

    /**
     * method to return customer country name based on division id
     * @return customer country name
     */
    public String getCountryName(){
        try {
            String countrySQL = "SELECT * FROM countries JOIN first_level_divisions ON Countries.Country_ID = first_level_divisions.Country_ID WHERE Division_ID = " + customerDivisionID;
            PreparedStatement ps = DatabaseConnection.connection.prepareStatement(countrySQL);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                customerCountryName = result.getString("Country");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerCountryName;
    }

    /**
     * Method to return first level division in order to populate combo box
     * @return customerFirstLevelDivision
     */
    public FirstLevelDivisions getFirstLevelDivision(){
        try {
            String fldivisionSQL = "SELECT * FROM first_level_divisions WHERE Division_ID = " + customerDivisionID;
            PreparedStatement ps = DatabaseConnection.connection.prepareStatement(fldivisionSQL);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                customerFirstLevelDivision = new FirstLevelDivisions(
                        result.getInt("Division_ID"),
                        result.getString("division"),
                        result.getInt("Country_ID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerFirstLevelDivision;
    }

    /**
     * Method to return the customer Country as a Country object in order to populate combo box
     * @return customerCountry
     */
    public Country getCustomerCountry(){
        try {
            String countrySQL = "SELECT * FROM countries JOIN first_level_divisions ON Countries.Country_ID = first_level_divisions.Country_ID WHERE Division_ID = " + customerDivisionID;
            PreparedStatement ps = DatabaseConnection.connection.prepareStatement(countrySQL);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                customerCountry = new Country(
                        result.getInt("Country_ID"),
                        result.getString("Country")
                        );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerCountry;
    }

    /**
     * return customerName
     * @return cursomterName
     */
    public String toString(){
        return customerName;
    }

}

