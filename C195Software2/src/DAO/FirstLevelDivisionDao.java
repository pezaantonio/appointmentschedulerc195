package DAO;
/**
 * This is the class to hold the firstLevelDivisions Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to handle all database queries for First level Divisions
 */
public class FirstLevelDivisionDao implements DataAccess{

    private static ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();

    /**
     * Method to get all first level division Objects
     * @return ObservableList First Level Divisions
     */
    @Override
    public ObservableList<FirstLevelDivisions> getAll() {
        //ObservableList<String> countryComboBox = FXCollections.observableArrayList();
        try {
            firstLevelDivisionsList.clear();
            String divisionSQL = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = DatabaseConnection.connection.prepareStatement(divisionSQL);
            ResultSet result = ps.executeQuery();

            while (result.next()){
                int divisionID = result.getInt("Division_ID");
                String divisionName = result.getString("division");
                int countryID = result.getInt("Country_ID");
                firstLevelDivisionsList.add(new FirstLevelDivisions(divisionID,divisionName,countryID));
            }
            ps.close();
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisionsList;
    }

    /**
     * Method to insert a first level division into the database
     * @param o
     * @return false
     */
    @Override
    public boolean insert(Object o) {
        return false;
    }

    /**
     * Method to update a selected first level division
     * @param id
     * @param o
     * @return false
     */
    @Override
    public boolean update(int id, Object o) {
        return false;
    }

    /**
     * Method to delete a selected first level division
     * @param id
     * @return false
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

    /**
     * Returns firstleveldivisions list using getAll()
     * @return
     */
    public static ObservableList<FirstLevelDivisions> getFirstLevelDivisionsList(){
        if (firstLevelDivisionsList.size() == 0){
            firstLevelDivisionsList = new FirstLevelDivisionDao().getAll();
        }
        return firstLevelDivisionsList;
    }

    /**
     * Method to return a list of countries based on the country division
     * @param countryID
     * @return ObservableList FirstLevelDivisions
     */
    public static ObservableList<FirstLevelDivisions> getCountryDivision(int countryID){
        ObservableList<FirstLevelDivisions> filteredList = FXCollections.observableArrayList();
        for (FirstLevelDivisions div : getFirstLevelDivisionsList()){
            if (div.getCountryID() == countryID){
                filteredList.add(div);
            }
        }
        return filteredList;
    }
}
