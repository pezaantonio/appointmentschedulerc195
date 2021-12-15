package model;

import DAO.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int userID;
    private String username;
    private String password;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    protected int userIDFromUsername;

    public User(int userID, String username){
        this(userID, username, "", "","","","");
    }

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

    /*
    * returns user ID
    * @return userID
    * */
    public int getUserID() {
        return userID;
    }

    /*
    * sets userID
    * @param userID
    * */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /*
    * returns username
    * @return username
    * */
    public String getUsername() {
        return username;
    }

    /*
    * sets username
    * @param username
    * */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
    * returns password
    * @return password
    * */
    public String getPassword() {
        return password;
    }

    /*
    * sets password
    * @param password
    * */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    * sets createDate
    * @param createDate
    * */
    public String getCreateDate() {
        return createDate;
    }

    /*
    * sets createDate
    * @param createDate
    * */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /*
    * returns createdBY
    * @return createdBy
    */
    public String getCreatedBy() {
        return createdBy;
    }

    /*
    * sets createdBy
    * @return createdBy
    * */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /*
    * returns lastUpdate
    * @return lastUpdate
    * */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /*
    * sets last Update
    * @param lastUpdate
    * */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /*
    * returns lastupdateby
    * @return lastUpdatedBy
    * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /*
    * setes lastupdatedby
    * @param lastUpdatedBy
    * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String toString(){
        return username;
    }
}