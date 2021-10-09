package model;

import java.sql.Date;

public class FirstLevelDivisions{
    
    private int divisionID;
    private String division;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /*
     * Constructor
     */
    public FirstLevelDivisions(int divisionID, String division, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy, int countryID){
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
    public Date getCreateDate() {
        return createDate;
    }

    /*
    * set create Date
    * @param createDate
    * */
    public void setCreateDate(Date createDate) {
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
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /*
    * set lastUPdate
    * @param lastUpdate
    * */
    public void setLastUpdate(Date lastUpdate) {
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