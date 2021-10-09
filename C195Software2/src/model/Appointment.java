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

}