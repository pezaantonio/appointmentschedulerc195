package controller;

import DAO.AppointmentDao;
import DAO.ContactDao;
import DAO.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    public TableColumn ReportColumnLeft;
    public AnchorPane Schedules;
    public Button ReportsMainMenuButton;
    public TableView ReportTableTop;
    public TableColumn ReportColumnCenter;
    @FXML
    private TableView<Appointment> AppointmentScheduleTable;
    @FXML
    private TableColumn<Appointment, String> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String> TitleColumn;
    @FXML
    private TableColumn<Appointment, String> DescriptionColumn;
    @FXML
    private TableColumn<Appointment, String> TypeColumn;
    @FXML
    private TableColumn<Appointment, String> StartTimeColumn;
    @FXML
    private TableColumn<Appointment, String> EndTimeColumn;
    @FXML
    private TableColumn<Appointment, String> CustIdColumn;
    @FXML
    private ComboBox<Contact> ContactComboBox;
    @FXML
    private ComboBox<Month> MonthComboBox;
    @FXML
    private ComboBox<Appointment> TypeComboBox;

    @FXML
    private ObservableList<Appointment> appointmentList;
    private ObservableList<Month> monthList;

    private Appointment appointment;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactComboBox.setItems(new ContactDao().getAll());
        MonthComboBox.setItems(listOfMonths());

        try {
            TypeComboBox.setItems(AppointmentDao.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void reportsByTypeAndMonth() throws SQLException {
        String countByType = "SELECT COUNT(*) FROM (SELECT DISTINCT Type FROM appointments) as report1";
        PreparedStatement countyByTypeSQL = DatabaseConnection.connection.prepareStatement(countByType);



        //SELECT * from appointments
        //WHERE EXTRACT(MONTH from Start)
    }

    /**
     * This method will return a list of appointments based on contact id
     * @param contact
     * @return ObservableList<Appointment>
     * @throws SQLException
     */
    public ObservableList<Appointment> reportsByContact(Contact contact) throws SQLException {
        appointmentList = FXCollections.observableArrayList();
        String reportsByContact = "SELECT * from appointments join contacts on contacts.Contact_ID = appointments.Contact_ID WHERE contacts.Contact_ID = ?";
        PreparedStatement reportsByContactSQL = DatabaseConnection.connection.prepareStatement(reportsByContact);

        reportsByContactSQL.setInt(1, contact.getContactID());
        ResultSet result = reportsByContactSQL.executeQuery();

        while (result.next()) {
            appointment = new Appointment(
                    result.getInt("Appointment_ID"),
                    result.getString("Title"),
                    result.getString("Description"),
                    result.getString("Location"),
                    result.getString("Type"),
                    result.getTimestamp("Start").toLocalDateTime(),
                    result.getTimestamp("End").toLocalDateTime(),
                    result.getTimestamp("Create_Date").toLocalDateTime(),
                    result.getString("Created_By"),
                    result.getTimestamp("Last_Update").toLocalDateTime(),
                    result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"),
                    result.getInt("User_ID"),
                    result.getInt("Contact_ID"));

            appointmentList.add(appointment);;
        }
        reportsByContactSQL.close();
        return appointmentList;

    }

    /**
     * Once a contact is selected, it will generate the report of their upcoming appointments
     * @throws SQLException
     */
    public void onContactSelect() throws SQLException {

        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentTitle"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentDescription"));
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentStart"));
        EndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentEnd"));
        CustIdColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentCustId"));

        AppointmentScheduleTable.setItems(reportsByContact(ContactComboBox.getValue()));

    }

    /**
     * Uses the Month class and extracts all the months and puts them into an observable list
     * @return monthList
     */
    public ObservableList<Month> listOfMonths(){
        monthList = FXCollections.observableArrayList();

        for(Month month : Month.values()){
            monthList.add(month);
        }

        return monthList;
    }

    public void onMonthSelect(){
    }

    public void onTypeSelect(){
    }

    public void onGenerateCount(){
    }

    /**
     * Sends user to the main menu
     * @param actionEvent
     * @throws IOException
     */
    public void toUsermain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/usermain.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();
    }

}
