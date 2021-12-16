package model;
/**
 * Class to hold FirstLevelDivisions
 */

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Class to hold firstLevelDivisions from the database
 */
public class FirstLevelDivisions {

    private int divisionID;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * Parameterized Constructor
     * @param divisionID
     * @param division
     * @param countryID
     */
    public FirstLevelDivisions(int divisionID, String division, int countryID) {
        this(divisionID, division, LocalDateTime.now(), "", LocalDateTime.now(), "", countryID);
    }

    /**
     * Constructor
     * @param divisionID
     * @param division
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryID
     */
    public FirstLevelDivisions(int divisionID, String division, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * return divisionID
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * set divisionID
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * return division
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * set division
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * return createDate
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * set createDate
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * return createdBy
     * @return createBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * sets createdBy
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * return lastUpdate
     * @return
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * sets lastUpdate
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * returns lastUpdatedBy
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * set lastUpdatedBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * return countryID
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * set countryID
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * return division
     * @return division
     */
    public String toString(){
        return division;
    }
}