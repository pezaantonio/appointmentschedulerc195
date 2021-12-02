package DAO;
/**
 * This is the class to hold the country Data Access Object
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

public class CountryDao implements DataAccess{

    private ObservableList<Country> countryList = FXCollections.observableArrayList();
    protected int countryID;

    @Override
    public ObservableList<Country> getAll() {
        try {
            countryList.clear();
            String countriesSQL = "SELECT * FROM countries";
            PreparedStatement ps = DatabaseConnection.connection.prepareStatement(countriesSQL);
            ResultSet result = ps.executeQuery();

            while (result.next()){
                countryID = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                countryList.add(new Country(countryID, countryName));
            }
            ps.close();
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }
    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean update(int id, Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    /**
     * Returns firstleveldivisions list using getAll()
     * @return
     */
    public ObservableList<Country> getCountryList(){
        if (countryList.size() == 0){
            countryList = getAll();
        }
        return countryList;
    }

}
