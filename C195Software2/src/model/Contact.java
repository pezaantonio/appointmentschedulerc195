package model;

public class Contact {

    private int contactID;
    private String contactName;
    private String contactEmail;

    /*
     * Constructor
     */
    public Contact(int contactID, String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
}