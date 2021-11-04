package model;

public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerCity;
    private String customerZip;
    private String customerPhone;
    private String customerCreateDate;
    private String customerCreatedBy;
    private String customerLastUpdate;
    private String customerLastUpdatedBy;
    private String customerDivisionID;

    public String getCustomerCreateDate() {
        return customerCreateDate;
    }

    public void setCustomerCreateDate(String customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }

    public String getCustomerLastUpdate() {
        return customerLastUpdate;
    }

    public void setCustomerLastUpdate(String customerLastUpdate) {
        this.customerLastUpdate = customerLastUpdate;
    }

    public String getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    public void setCustomerLastUpdatedBy(String customerLastUpdatedBy) {
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    public String getCustomerDivisionID() {
        return customerDivisionID;
    }

    public void setCustomerDivisionID(String customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }

    public String getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    public void setCustomerCreatedBy(String customerCreatedBy) {
        this.customerCreatedBy = customerCreatedBy;
    }

    /*
     *
     * Constructor
     */
    public Customer(){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
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
     * @param customerZip
     */
    public void setCustomerZip(String customerZip){
        this.customerZip = customerZip;
    }

    /*
     * Function to return customerZip
     * @return customerZip
     */
    public String getCustomerZip(){
        return customerZip;
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
