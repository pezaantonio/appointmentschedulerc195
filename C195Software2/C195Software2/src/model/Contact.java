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

    /*
    * Returns contactID
    * @return contactID
    * */
    public int getContactID() {
        return contactID;
    }

    /*
    * Sets contactID
    * @param contactID
    * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /*
    * retruns contactName
    * @return contactName
    * */
    public String getContactName() {
        return contactName;
    }

    /*
    * sets contact Name
    * @param contactName
    * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /*
    * returns contactEmail
    * @return contactEmail
    * */
    public String getContactEmail() {
        return contactEmail;
    }

    /*
    * sets contactEmail
    * @param contactEmail
    * */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String toString(){
        return contactName;
    }

}