package model;

import DAO.DatabaseConnection;
import DAO.UserDao;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {

    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private LocalDateTime appointmentCreateDate;
    private String appointmentCreatedBy;
    private LocalDateTime appointmentLastUpdate;
    private String appointmentUpdatedBy;
    private int appointmentCustId;
    private int appointmentUserId;
    private int appointmentContactId;

    protected Contact contactFromContactId;
    protected Customer customerFromCustomerId;

    public Appointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, LocalDateTime appointmentCreateDate, String appointmentCreatedBy, LocalDateTime appointmentLastUpdate, String appointmentUpdatedBy, int appointmentCustId, int appointmentUserId, int appointmentContactId) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentCreateDate = appointmentCreateDate;
        this.appointmentCreatedBy = appointmentCreatedBy;
        this.appointmentLastUpdate = appointmentLastUpdate;
        this.appointmentUpdatedBy = appointmentUpdatedBy;
        this.appointmentCustId = appointmentCustId;
        this.appointmentUserId = appointmentUserId;
        this.appointmentContactId = appointmentContactId;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public LocalDateTime getAppointmentCreateDate() {
        return appointmentCreateDate;
    }

    public void setAppointmentCreateDate(LocalDateTime appointmentCreateDate) {
        this.appointmentCreateDate = appointmentCreateDate;
    }

    public String getAppointmentCreatedBy() {
        return appointmentCreatedBy;
    }

    public void setAppointmentCreatedBy(String appointmentCreatedBy) {
        this.appointmentCreatedBy = appointmentCreatedBy;
    }

    public LocalDateTime getAppointmentLastUpdate() {
        return appointmentLastUpdate;
    }

    public void setAppointmentLastUpdate(LocalDateTime appointmentLastUpdate) {
        this.appointmentLastUpdate = appointmentLastUpdate;
    }

    public String getAppointmentUpdatedBy() {
        return appointmentUpdatedBy;
    }

    public void setAppointmentUpdatedBy(String appointmentUpdatedBy) {
        this.appointmentUpdatedBy = appointmentUpdatedBy;
    }

    public int getAppointmentCustId() {
        return appointmentCustId;
    }

    public void setAppointmentCustId(int appointmentCustId) {
        this.appointmentCustId = appointmentCustId;
    }

    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }

    public int getAppointmentContactId() {
        return appointmentContactId;
    }

    public void setAppointmentContactId(int appointmentContactId) {
        this.appointmentContactId = appointmentContactId;
    }

    private static boolean appointmentComing = false;


    /**
     * Method to get the entire contact based on the contact id
     * @return Contact
     * @throws SQLException
     */
    public Contact getContactFromContactId() throws SQLException {
        String contactQuery = "SELECT * FROM contacts WHERE Contact_ID = " + appointmentContactId;
        PreparedStatement contactQuerySQL = DatabaseConnection.connection.prepareStatement(contactQuery);
        ResultSet result = contactQuerySQL.executeQuery();

        while (result.next()) {
            contactFromContactId = new Contact(
                    appointmentContactId,
                    result.getString("Contact_Name"),
                    result.getString("Email")
            );
        }
        return contactFromContactId;
    }

    /**
     * method to return the entire customer based on customer Id
     * @return Customer
     * @throws SQLException
     */
    public Customer getCustomerFromCustomerId() throws SQLException{
        String customerQuery = "SELECT * FROM customers WHERE Customer_ID = " + appointmentCustId;
        PreparedStatement customerQuerySQL = DatabaseConnection.connection.prepareStatement(customerQuery);
        ResultSet result = customerQuerySQL.executeQuery();

        while (result.next()) {
            customerFromCustomerId = new Customer(
                    appointmentCustId,
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
        }
        return customerFromCustomerId;
    }

    public String toString(){
        return appointmentType;
    }

    /**
     * Method is used to check if the user has upcoming appointments
     * @return boolean apppointmentComing
     * @throws SQLException
     */
    public static boolean appointmentTimeCheck() throws SQLException {
        LocalDateTime appointmentTime = null;
        String timeCheck = "SELECT * FROM appointments WHERE User_ID = " + UserDao.getUserId();
        PreparedStatement timeCheckSQL = DatabaseConnection.connection.prepareStatement(timeCheck);
        ResultSet result = timeCheckSQL.executeQuery();

        while(result.next()){
            appointmentTime = result.getTimestamp("Start").toLocalDateTime();
        }

        if(appointmentTime.isAfter(LocalDateTime.now()) && appointmentTime.isBefore(LocalDateTime.now().plusMinutes(15))){
            appointmentComing = true;
        }

        return appointmentComing;

    }
}

