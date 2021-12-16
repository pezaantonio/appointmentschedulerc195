package model;
/**
 * Class to hold contact objects
 */

/**
 * Class to hold contact objects from the database
 */
public class Contact {

    private int contactID;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor
     * @param contactID
     * @param contactName
     * @param contactEmail
     */
    public Contact(int contactID, String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
    * Returns contactID
    * @return contactID
    * */
    public int getContactID() {
        return contactID;
    }

    /**
    * Sets contactID
    * @param contactID
    * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
    * returns contactName
    * @return contactName
    * */
    public String getContactName() {
        return contactName;
    }

    /**
    * sets contact Name
    * @param contactName
    * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
    * returns contactEmail
    * @return contactEmail
    * */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
    * sets contactEmail
    * @param contactEmail
    * */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Method to return contactName into a string
     * @return contactName
     */
    public String toString(){
        return contactName;
    }

}