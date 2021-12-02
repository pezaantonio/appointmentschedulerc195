package model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Country{
    
    private int countryID;
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    public Country(int countryID, String country) {
        this(countryID, country, LocalDateTime.now(), "", LocalDateTime.now(),"");
    }

    /*
     * Constructor
     */
    public Country(int countryID, String country, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy){
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
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /*
    * sets createDate
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
    * sets last Update
    * @param lastUpdate
    * */
    public void setLastUpdate(LocalDateTime lastUpdate) {
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

    public String toString(){
        return country;
    }
}