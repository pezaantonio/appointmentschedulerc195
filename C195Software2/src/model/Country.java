package model;

import java.sql.Date;

public class Country{
    
    private int countryID;
    private String country;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    /*
     * Constructor
     */
    public Country(int countryID, String country, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy){
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
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

    /*
    * returns country
    * @return country
    * */
    public String getCountry() {
        return country;
    }

    /*
    * sets country
    * @param country
    * */
    public void setCountry(String country) {
        this.country = country;
    }

    /*
    * returns create date
    * @return createDate
    * */
    public Date getCreateDate() {
        return createDate;
    }

    /*
    * sets createDate
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
    * sets last Update
    * @param lastUpdate
    * */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /*
    * returns last updated by
    * @return lastUpdatedBy
    * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /*
    * sets lastUpdatedBy
    * @param lastUpdatedBy
    * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}