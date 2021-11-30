package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerCity;
    private String customerPostalCode;
    private String customerPhone;
    private LocalDateTime customerCreateDate;
    private String customerCreatedBy;
    private LocalDateTime customerLastUpdate;
    private String customerLastUpdatedBy;
    private int customerDivisionID;

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

    public LocalDateTime getCustomerCreateDate() {
        return customerCreateDate;
    }

    public void setCustomerCreateDate(LocalDateTime customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }

    public LocalDateTime getCustomerLastUpdate() {
        return customerLastUpdate;
    }

    public void setCustomerLastUpdate(LocalDateTime customerLastUpdate) {
        this.customerLastUpdate = customerLastUpdate;
    }

    public String getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    public void setCustomerLastUpdatedBy(String customerLastUpdatedBy) {
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    public int getCustomerDivisionID() {
        return customerDivisionID;
    }

    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }

    public String getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    public void setCustomerCreatedBy(String customerCreatedBy) {
        this.customerCreatedBy = customerCreatedBy;
    }

    /*
     * Function to set customer ID
     * @param customerID
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /*
     * Function to return customer ID
     * @return customerID
     */
    public int getCustomerID(){
        return customerID;
    }

    /*
     * Function to set Customer Name
     * @param customerName
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /*
     * Function to return customer Name
     * @return customerName
     */
    public String getCustomerName(){
        return customerName;
    }

    /*
     * Function to set customer Address
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress){
        this.customerAddress = customerAddress;
    }

    /*
     * Function to return customer Address
     * @return customerAddress
     */
    public String getCustomerAddress(){
        return customerAddress;
    }

    /*
     * Function to set customerCity
     * @param customerID
     */
    public void setCustomerCity(String customerCity){
        this.customerCity = customerCity;
    }

    /*
     * Function to return customer City
     * @return customerCity
     */
    public String getCustomerCity(){
        return customerCity;
    }

    /*
     * Function to set customer Zip
     * @param customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode){
        this.customerPostalCode = customerPostalCode;
    }

    /*
     * Function to return customerPostalCode
     * @return customerPostalCode
     */
    public String getCustomerPostalCode(){
        return customerPostalCode;
    }

    /*
     * Function to set customerPhone
     * @param customerPhone
     */
    public void setCustomerPhone(String customerPhone){
        this.customerPhone = customerPhone;
    }

    /*
     * Function to return customerPhone
     * @return customerPhone
     */
    public String getCustomerPhone(){
        return customerPhone;
    }
}
