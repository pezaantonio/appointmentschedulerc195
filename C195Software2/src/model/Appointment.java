package model;

import DAO.DatabaseConnection;
import DAO.UserDao;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Class to hold appointment information that comes from the database
 */
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
    private User userFromUserId;
    private static LocalDateTime appointmentTime;

    /**
     * Construcotr
     * @param appointmentID
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentLocation
     * @param appointmentType
     * @param appointmentStart
     * @param appointmentEnd
     * @param appointmentCreateDate
     * @param appointmentCreatedBy
     * @param appointmentLastUpdate
     * @param appointmentUpdatedBy
     * @param appointmentCustId
     * @param appointmentUserId
     * @param appointmentContactId
     */
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

    /**
     * returns appointmentID
     * @return appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets the appointment ID
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * returns appointmentTitle
     * @return appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * sets appointmentTitle
     * @param appointmentTitle
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * returns appointmentDescription
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * sets appointmentDescription
     * @param appointmentDescription
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * returns appointmentLocation
     * @return
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * sets appopintmentLocation
     * @param appointmentLocation
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * returns appointmentType
     * @return
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * sets appointmentType
     * @param appointmentType
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    /**
     * sets AppointmentStart
     * @param appointmentStart
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * returns AppointmentEnd
     * @return appointmentEnd
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    /**
     * sets appointmentEnd
     * @param appointmentEnd
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * returns appointmentCreateDate
     * @return appointmentCreateDate
     */
    public LocalDateTime getAppointmentCreateDate() {
        return appointmentCreateDate;
    }

    /**
     * sets appointmentCreateDate
     * @param appointmentCreateDate
     */
    public void setAppointmentCreateDate(LocalDateTime appointmentCreateDate) {
        this.appointmentCreateDate = appointmentCreateDate;
    }

    /**
     * returns appointmentCreatedBy
     * @return appointmentCreatedBy
     */
    public String getAppointmentCreatedBy() {
        return appointmentCreatedBy;
    }

    /**
     * sets appointmentCreatedBy
     * @param appointmentCreatedBy
     */
    public void setAppointmentCreatedBy(String appointmentCreatedBy) {
        this.appointmentCreatedBy = appointmentCreatedBy;
    }

    /**
     * returns appointmentLastUpdate
     * @return appointmentLastUpdate
     */
    public LocalDateTime getAppointmentLastUpdate() {
        return appointmentLastUpdate;
    }

    /**
     * sets appointmentLastupdate
     * @param appointmentLastUpdate
     */
    public void setAppointmentLastUpdate(LocalDateTime appointmentLastUpdate) {
        this.appointmentLastUpdate = appointmentLastUpdate;
    }

    /**
     * returns appointmentUpdatedBy
     * @return
     */
    public String getAppointmentUpdatedBy() {
        return appointmentUpdatedBy;
    }

    /**
     * sets appointmentUpdateBy
     * @param appointmentUpdatedBy
     */
    public void setAppointmentUpdatedBy(String appointmentUpdatedBy) {
        this.appointmentUpdatedBy = appointmentUpdatedBy;
    }

    /**
     * returns appointmentCustId
     * @return appointmentCustId
     */
    public int getAppointmentCustId() {
        return appointmentCustId;
    }

    /**
     * sets appointmentCustId
     * @param appointmentCustId
     */
    public void setAppointmentCustId(int appointmentCustId) {
        this.appointmentCustId = appointmentCustId;
    }

    /**
     * returns appointmentUserId
     * @return appointmentUserId
     */
    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    /**
     * sets appointmentUserId
     * @param appointmentUserId
     */
    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }

    /**
     * returns appointmentContactID
     * @return appointmentContactId
     */
    public int getAppointmentContactId() {
        return appointmentContactId;
    }

    /**
     * sets AppointmentContact Id
     * @param appointmentContactId
     */
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

    /**
     * returns appointmentType as a string
     * @return appointmentType
     */
    public String toString(){
        return appointmentType;
    }

    /**
     * Method is used to check if the user has upcoming appointments
     * @return boolean apppointmentComing
     * @throws SQLException
     */
    public static boolean appointmentTimeCheck() throws SQLException {
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

    /**
     * Method to get the username from the user ID in appointment ID
     * @return User userFromUserId
     * @throws SQLException
     */
    public User getApppointmentUser() throws SQLException {
        String userQuery = "SELECT * FROM Users WHERE User_ID = " + appointmentUserId;
        PreparedStatement userQuerySQL = DatabaseConnection.connection.prepareStatement(userQuery);
        ResultSet result = userQuerySQL.executeQuery();

        while(result.next()){
            userFromUserId = new User(
                    appointmentUserId,
                    result.getString("User_Name")
            );
        }
        return userFromUserId;
    }

    /**
     * Method will check the appointment's month
     * @return YearMonth appointmentYearMonth
     */
    public YearMonth getAppointmentMonth(){
        YearMonth appointmentYearMonth = YearMonth.from(getAppointmentStart());

        return appointmentYearMonth;
    }

    /**
     * Method will return the week number of the year  based on the date
     * @return int weekFromDay
     */
    public int getWeekFromDay(){
        LocalDate date = getAppointmentStart().toLocalDate();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekFromDay = date.get(weekFields.weekOfWeekBasedYear());

        return weekFromDay;
    }
}

