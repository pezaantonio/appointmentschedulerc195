package model;

public class User {

    private int userID;
    private String username;
    private String password;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    /*
     * Constructor
     */
    public User(int userID, String username, String password, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    
}