package controller;

import DAO.CustomerDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @FXML
    private TableView<Customer> CustomerTableView;
    @FXML
    private TableColumn<Customer, Integer> CustomerCustIdColumn;
    @FXML
    private TableColumn<Customer, String> CustomerCustNameColumn;
    @FXML
    private TableColumn<Customer, String> CustomerAddressColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPostalCodeColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPhoneColumn;
    @FXML
    private TableColumn<Customer, String> CustomerCreatedColumn;
    @FXML
    private TableColumn<Customer, String> CustomerLastUpdateColumn;
    @FXML
    private TableColumn<Customer, String> CustomerLastUpdatedByColumn;
    @FXML
    private TableColumn<Customer, String> CustomerCountryIdColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerCustNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        CustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        CustomerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPostalCode"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        CustomerCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerCreated"));
        CustomerLastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerLastUpdate"));
        CustomerLastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerLastUpdatedBy"));
        CustomerCountryIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerCountryIdColumn"));

        try {
            CustomerTableView.setItems(CustomerDao.getAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
