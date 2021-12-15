package controller;

import DAO.AppointmentDao;
import DAO.ContactDao;
import DAO.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import javax.security.auth.callback.Callback;
import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Lambda expression to count the amount of success and unsuccessful log in attempts
 */
interface LambdaInterface {

    int counter(int i);

}

public class ReportsController implements Initializable {
    public AnchorPane Schedules;
    public Button ReportsMainMenuButton;
    public Label countLabel;
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
    private Label successfulLabel;
    @FXML
    private Label unsuccessfulLabel;

    @FXML
    private ObservableList<Appointment> appointmentList;
    private ObservableList<Month> monthList;
    public ObservableList<String> countList;

    private Appointment appointment;

    public String success = " - Successful";
    public String unsuccess = " - Unsuccessful";

    public int successCounter;
    public int unsuccessCounter;


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

    public ObservableList<String> reportsByTypeAndMonth() throws SQLException {
        countList = FXCollections.observableArrayList();
        String countByType = "SELECT Count(*) FROM appointments WHERE EXTRACT(MONTH FROM Start) = ? AND Type = ?";
        PreparedStatement countByTypeSQL = DatabaseConnection.connection.prepareStatement(countByType);

        countByTypeSQL.setInt(1, MonthComboBox.getValue().getValue());
        countByTypeSQL.setString(2, TypeComboBox.getValue().getAppointmentType());

        ResultSet result = countByTypeSQL.executeQuery();

        while (result.next()){
            countList.add(result.getString("Count(*)"));
        }

        countByTypeSQL.close();

        return countList;

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

    /**
     * This method will count the appointments my month and type
     * @throws SQLException
     */
    public void onGenerateCount() throws SQLException {

        if(MonthComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a month");
            Optional<ButtonType> result = alert.showAndWait();
        }
        if(TypeComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a type");
            Optional<ButtonType> result = alert.showAndWait();
        }

        countLabel.setText(reportsByTypeAndMonth().get(0));
    }

    /**
     * Method to count how many times there are successful logins
     * Lambda expression is being used to increment the successCounter in order to return it to the reports interface
     * @throws FileNotFoundException
     */
    public void loginAuditorSuccess() throws FileNotFoundException {
        successCounter = 0;
        LambdaInterface sCount = i -> i + 1;
        File file = new File("./login_activity.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            if (scanner.nextLine().contains(success)){
                successCounter = sCount.counter(successCounter);
                System.out.println(successCounter);
            }
        }
    }

    /**
     * Method to count how many times there were unsuccessful logins
     * @throws FileNotFoundException
     */
    public void loginAuditorUnsuccess() throws FileNotFoundException {
        unsuccessCounter = 0;

        File file = new File("./login_activity.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            if (scanner.nextLine().contains(unsuccess)) {
                unsuccessCounter = unsuccessCounter + 1;
            }
        }
    }

    /**
     * Calls the methods to count how many successful and unsuccessful logins there are in the login_activity.txt log
     * @throws FileNotFoundException
     */
    public void onLoginAudit() throws FileNotFoundException {
        loginAuditorSuccess();
        loginAuditorUnsuccess();
        successfulLabel.setText(Integer.toString(successCounter));
        unsuccessfulLabel.setText(Integer.toString(unsuccessCounter));
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
