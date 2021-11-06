package model;

import java.sql.Date;
import java.time.LocalDateTime;

public class FirstLevelDivisions{
    
    private int divisionID;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * First Level Divisions fully parameterized constructor
     * @param divisionID
     * @param division
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryID
     */
    public FirstLevelDivisions(int divisionID, String division, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryID){
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /*
    * returns division ID
    * @return division ID
    * */
    public int getDivisionID() {
        return divisionID;
    }

    /*
    * sets division ID
    * @param division ID
    * */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /*
    * returns division
    * @return division
    * */
    public String getDivision() {
        return division;
    }

    /*
    * sets division
    * @param division
    * */
    public void setDivision(String division) {
        this.division = division;
    }

    /*
    * returns createDate
    * @return createDate
    * */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /*
    * set create Date
    * @param createDate
    * */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /*
    * returns createdBy
    * @return createdBy
    * */
    public String getCreatedBy() {
        return createdBy;
    }

    /*
    * sets createdBy
    * @param createdBy
    * */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /*
     * returns lastUpdate
     * @return lastUpdate
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /*
    * set lastUPdate
    * @param lastUpdate
    * */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /*
    * return lastUpdateBy
    * @return lastUpdatedBy
    * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /*
    * set lastupdatedBy
    * @param lastUpdatedBy
    * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /*
    * returns countryID
    * @return countryID
    * */
    public int getCountryID() {
        return countryID;
    }

    /*
    * sets countryID
    * @param countryID
    * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}