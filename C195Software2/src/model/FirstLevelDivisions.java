package model;

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
}