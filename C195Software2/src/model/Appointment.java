package model;

public class Appointment {

    private int appointmentID;
    private int appointmentCustID;
    private String appointmentTime;
    private String appointmentTitle;
    private String appointmentDesc;
    private String appointmentLoc;
    private String appointmentContact;

    /*
     * Constructor
     */
    public Appointment(int appointmentID, int appointmentCustID, String appointmentTime, String appointmentTitle, String appointmentDesc, String appointmentLoc, String appointmentContact){
        this.appointmentID = appointmentID;
        this.appointmentCustID = appointmentCustID;
        this.appointmentTime = appointmentTime;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDesc = appointmentDesc;
        this.appointmentLoc = appointmentLoc;
        this.appointmentContact = appointmentContact;
    }

    /*
    * Returns appointmentID
    * @return appointmentID
    * */
    public int getAppointmentID() {
        return appointmentID;
    }

    /*
    * sets appointmentID
    * @param appointmentID
    * */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /*
     * Returns appointmentCustID
     * @return appointmentCustId
     * */
    public int getAppointmentCustID() {
        return appointmentCustID;
    }

    /*
     * sets appointmentCustID
     * @param appointmentCustID
     * */
    public void setAppointmentCustID(int appointmentCustID) {
        this.appointmentCustID = appointmentCustID;
    }

    /*
     * Returns appointmentTime
     * @return appointmentTime
     * */
    public String getAppointmentTime() {
        return appointmentTime;
    }

    /*
     * sets appointmentTime
     * @param appointmentTime
     * */
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /*
     * Returns appointmentTitle
     * @return appointmentTitle
     * */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /*
     * sets appointmentTitle
     * @param appointmentTitle
     * */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /*
     * Returns appointmentDesc
     * @return appointmentDesc
     * */
    public String getAppointmentDesc() {
        return appointmentDesc;
    }

    /*
     * sets appointmentDesc
     * @param appointmentDesc
     * */
    public void setAppointmentDesc(String appointmentDesc) {
        this.appointmentDesc = appointmentDesc;
    }

    /*
     * Returns appointmentLoc
     * @return appointmentLoc
     *  */
    public String getAppointmentLoc() {
        return appointmentLoc;
    }

    /*
     * sets appointmentLoc
     * @param appointmentLoc
     * */
    public void setAppointmentLoc(String appointmentLoc) {
        this.appointmentLoc = appointmentLoc;
    }

    /*
    * Returns appointmentContact
    * @return appointmentContact
    * */
    public String getAppointmentContact() {
        return appointmentContact;
    }

    /*
     * sets appointmentContact
     * @param appointmentContact
     * */
    public void setAppointmentContact(String appointmentContact) {
        this.appointmentContact = appointmentContact;
    }
}