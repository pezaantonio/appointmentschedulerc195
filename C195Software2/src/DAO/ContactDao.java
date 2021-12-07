package DAO;
/**
 * This is the class to hold the contact Data Access Object
 * @author Antonio Peza
 *
 * */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao implements DataAccess{

    private ObservableList<Contact> contactList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Contact> getAll() {
        try {
            contactList.clear();
            String contactQuery = "SELECT * FROM contacts";
            PreparedStatement contactSQL = DatabaseConnection.connection.prepareStatement(contactQuery);
            ResultSet result = contactSQL.executeQuery();

            while(result.next()){
                int contactID = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                String contactEmail = result.getString("Email");

                contactList.add(new Contact(contactID,contactName, contactEmail));;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
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


}
