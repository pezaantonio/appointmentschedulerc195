package controller;

import DAO.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

import controller.LoginController;

/**
 * Method to handle the updateAppointmentsController.fxml
 */
public class UpdateAppointmentsController implements Initializable {

    @FXML
    private TextField AppointmentIdTextField;
    @FXML
    private TextField AppointmentTitleTextField;
    @FXML
    private TextField AppointmentDescriptionTextField;
    @FXML
    private TextField AppointmentLocationTextField;
    @FXML
    private TextField AppointmentTypeTextField;
    @FXML
    private DatePicker AppointmentDatePicker;
    @FXML
    private ComboBox<LocalTime> AppointmentStartComboBox;
    @FXML
    private ComboBox<LocalTime> AppointmentEndComboBox;
    @FXML
    private ComboBox<Contact> AppointmentContactComboBox;
    @FXML
    private ComboBox<Customer> AppointmentCustomerIDComboBox;
    @FXML
    private ComboBox<User> AppointmentUserIDComboBox;

    private Appointment appointmentToUpdate;

    private Appointment appointment;

    private AppointmentDao newAppointment;

    private boolean isValid;
    private boolean isTitleEmpty;
    private boolean isDescriptionEmpty;
    private boolean isLocationEmpty;
    private boolean isTypeEmpty;
    private boolean isDateEmpty;
    private boolean isStartEmpty;
    private boolean isEndEmpty;
    private boolean isCustEmpty;
    private boolean isUserEmpty;
    private boolean isContactEmpty;

    private boolean isNotOverlapping;
    private ZoneId localZoneId = ZoneId.systemDefault();
    private ZoneId eastZoneId = ZoneId.of("US/Eastern");
    private LocalDateTime appointmentEndDateTime;
    private LocalDateTime appointmentStartDateTime;

    String emptyTitle = "";
    String emptyDesc = "";
    String emptyLoc = "";
    String emptyType = "";
    String emptyDate = "";
    String emptyStart = "";
    String emptyEnd = "";
    String emptyCust = "";
    String emptyUser = "";
    String emptyContact = "";


    /**
     * Method to inialize the updateappointmentscontroller with the selected appointment information
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentToUpdate = AppointmentsController.getAppointmentToUpdate();

        AppointmentIdTextField.setText(Integer.toString(appointmentToUpdate.getAppointmentID()));
        AppointmentTitleTextField.setText(appointmentToUpdate.getAppointmentTitle());
        AppointmentDescriptionTextField.setText(appointmentToUpdate.getAppointmentDescription());
        AppointmentLocationTextField.setText(appointmentToUpdate.getAppointmentLocation());
        AppointmentTypeTextField.setText(appointmentToUpdate.getAppointmentType());
        AppointmentDatePicker.setValue(LocalDate.from(appointmentToUpdate.getAppointmentStart()));
        AppointmentStartComboBox.setValue(LocalTime.from(appointmentToUpdate.getAppointmentStart()));
        AppointmentEndComboBox.setValue(LocalTime.from(appointmentToUpdate.getAppointmentEnd()));
        try {
            AppointmentCustomerIDComboBox.setValue(appointmentToUpdate.getCustomerFromCustomerId());
            AppointmentUserIDComboBox.setValue(appointmentToUpdate.getApppointmentUser());
            AppointmentContactComboBox.setValue(appointmentToUpdate.getContactFromContactId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AppointmentContactComboBox.setItems(new ContactDao().getAll());
        AppointmentStartComboBox.setItems(new DateTime().getStartList());
        AppointmentEndComboBox.setItems(new DateTime().getStartList());
        try {
            AppointmentCustomerIDComboBox.setItems(new CustomerDao().getAll());
            AppointmentUserIDComboBox.setItems(new UserDao().getAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method will change the end time based on the start time selection
     * @param actionEvent
     */
    public void onStartTimeSelect(ActionEvent actionEvent){
        //AppointmentEndComboBox.setItems(new DateTime().getEndList(AppointmentStartComboBox.getSelectionModel().getSelectedItem()));
    }
    /**
     * Method to send user to appointments page
     * @param actionEvent
     * @throws IOException
     */
    public void toAppointments(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method grabs the information from text fields and creates an Appointment object with it that will be sent to DB
     * @return
     * @throws SQLException
     */
    public Appointment onUpdateAppointment(ActionEvent actionEvent) throws SQLException {
        try {
            appointment = new Appointment(
                    Integer.parseInt(AppointmentIdTextField.getText()),
                    AppointmentTitleTextField.getText(),
                    AppointmentDescriptionTextField.getText(),
                    AppointmentLocationTextField.getText(),
                    AppointmentTypeTextField.getText(),
                    getAppointmentStartDateTime(appointmentDate(), AppointmentStartComboBox.getValue()),
                    getAppointmentEndDateTime(appointmentDate(), AppointmentEndComboBox.getValue()),
                    LocalDateTime.now(),
                    UserDao.getUserName(),
                    LocalDateTime.now(),
                    UserDao.getUserName(),
                    AppointmentCustomerIDComboBox.getValue().getCustomerID(),
                    AppointmentUserIDComboBox.getValue().getUserID(),
                    AppointmentContactComboBox.getValue().getContactID()
            );
        } catch (NullPointerException e) {
            if(isValidAppointment() == false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(
                        emptyTitle +
                                "\n" + emptyDesc +
                                "\n" + emptyLoc +
                                "\n" + emptyType +
                                "\n" + emptyDate +
                                "\n" + emptyStart +
                                "\n" + emptyEnd +
                                "\n" + emptyCust +
                                "\n" + emptyUser +
                                "\n" + emptyContact
                );
                Optional<ButtonType> result = alert.showAndWait();
            }
        }

        LocalDate apptStartDate = AppointmentDatePicker.getValue();
        LocalTime apptStartTime = AppointmentStartComboBox.getValue();
        LocalTime apptEndTime = AppointmentEndComboBox.getValue();

        if (isOverlapping()) {
            newAppointment = new AppointmentDao();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("This customer has an overlapping appointment. Please pick a new time");
            Optional<ButtonType> result = alert.showAndWait();
        }


        if (isValidAppointment()) {
            if (apptStartTime.isBefore(apptEndTime)) {
                if (isBusinessHours(apptStartDate, apptStartTime, apptEndTime)){
                    try {
                        if (newAppointment.insert(appointment)) {
                            saveRedirect(actionEvent);
                        }
                    } catch (NullPointerException e) {
                        System.out.println("there was an overlap");
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("The appointment cannot be scheduled outside of working business hours. Business hours are Monday - Friday 8am - 10pm EST");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("End time can not be before start time");
                Optional<ButtonType> result = alert.showAndWait();
                isValid = false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(
                    emptyTitle +
                            "\n" + emptyDesc +
                            "\n" + emptyLoc +
                            "\n" + emptyType +
                            "\n" + emptyDate +
                            "\n" + emptyStart +
                            "\n" + emptyEnd +
                            "\n" + emptyCust +
                            "\n" + emptyUser +
                            "\n" + emptyContact
            );
            Optional<ButtonType> result = alert.showAndWait();
        }
        return appointment;
    }

    /**
     * Method to save and then redirect back to the appointments menu
     * @param actionEvent
     */
    public void saveRedirect(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Successful");
        alert.setContentText("Save Successful, returning to Appointments menu");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("view/appointments.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Method to verify if appointment is within business hours and on a busines day
     * @param apptLocalStartDate
     * @param apptStartTime
     * @return isBusinessHours
     */
    public boolean isBusinessHours(LocalDate apptLocalStartDate, LocalTime apptStartTime, LocalTime apptEndTime) {
        boolean businessTime = false;
        boolean businessDay = false;
        boolean withinBusinessHours = false;
        boolean isBeforeESTend = false;

        LocalDateTime appointmentStartDateTime = LocalDateTime.of(apptLocalStartDate, apptStartTime);
        LocalDateTime appointmentEndDateTime = LocalDateTime.of(apptLocalStartDate, apptEndTime);

        LocalTime localTimeStart = LocalTime.of(7,50);
        LocalTime localTimeEnd = LocalTime.of(22,0);
        ZonedDateTime eastCoastStart = ZonedDateTime.of(apptLocalStartDate, localTimeStart, eastZoneId);
        ZonedDateTime eastCoastEnd = ZonedDateTime.of(apptLocalStartDate, localTimeEnd, eastZoneId);

        ZonedDateTime localToEast = appointmentStartDateTime.atZone(localZoneId).withZoneSameInstant(eastZoneId);
        ZonedDateTime endLocalToEast = appointmentEndDateTime.atZone(localZoneId).withZoneSameInstant(eastZoneId);

        DayOfWeek startDayOfWeek = DayOfWeek.of(apptLocalStartDate.get(ChronoField.DAY_OF_WEEK));

        if(localToEast.isAfter(eastCoastStart) && localToEast.isBefore(eastCoastEnd)){
            System.out.println("Inside of business hours");
            businessTime = true;
        }
        if(endLocalToEast.isBefore(eastCoastEnd)){
            System.out.println("End is before 10pm EST");
            isBeforeESTend = true;
        }
        if (startDayOfWeek != DayOfWeek.SATURDAY && startDayOfWeek != DayOfWeek.SUNDAY) {
            System.out.println("on weekday");
            businessDay = true;
        }

        if ((businessDay && businessTime) && isBeforeESTend) {
            withinBusinessHours = true;
        }
        return withinBusinessHours;
    }

    /**
     * Method to verify that no fields are left empty
     * @return isValid
     */
    public boolean isValidAppointment(){
        isValid = true;
        isTitleEmpty = false;
        isDescriptionEmpty = false;
        isLocationEmpty = false;
        isTypeEmpty = false;
        isDateEmpty = false;
        isStartEmpty = false;
        isEndEmpty = false;
        isCustEmpty = false;
        isUserEmpty = false;
        isContactEmpty = false;

        if(AppointmentTitleTextField.getText().isEmpty()){
            emptyTitle = "Please enter a title";
            isTitleEmpty = true;
            isValid = false;
        }
        if(AppointmentDescriptionTextField.getText().isEmpty()){
            emptyDesc = "Please enter a description";
            isDescriptionEmpty = true;
            isValid = false;
        }
        if(AppointmentLocationTextField.getText().isEmpty()){
            emptyLoc = "Please enter a location";
            isLocationEmpty = true;
            isValid = false;
        }
        if(AppointmentTypeTextField.getText().isEmpty()){
            emptyType = "Please enter a type";
            isTypeEmpty = true;
            isValid = false;
        }
        if(AppointmentDatePicker.getValue() == null){
            emptyDate = "Please choose a date";
            isDateEmpty = true;
            isValid = false;
        }
        if(AppointmentStartComboBox.getValue() == null){
            emptyStart = "Please choose a start time";
            isStartEmpty = true;
            isValid = false;
        }
        if(AppointmentEndComboBox.getValue() == null){
            emptyEnd = "Please enter an end time";
            isEndEmpty = true;
            isValid = false;
        }
        if(AppointmentCustomerIDComboBox.getValue() == null){
            emptyCust = "Please select a customer";
            isCustEmpty = true;
            isValid = false;
        }
        if(AppointmentUserIDComboBox.getValue() == null){
            emptyUser = "Please select a user";
            isUserEmpty = true;
            isValid = false;
        }
        if(AppointmentContactComboBox.getValue() == null){
            emptyContact = "Please select a contact";
            isContactEmpty = true;
            isValid = false;
        }

        return isValid;
    }

    /**
     * Method to determine if the customer has any overlapping appointments
     * @return
     * @throws SQLException
     */
    public boolean isOverlapping() throws SQLException {
        isNotOverlapping = true;
        ObservableList<Appointment> allAppoints = AppointmentDao.getAllAppointments();
        LocalDateTime aStart = getAppointmentStartDateTime(AppointmentDatePicker.getValue(), AppointmentStartComboBox.getValue());
        LocalDateTime aEnd = getAppointmentEndDateTime(AppointmentDatePicker.getValue(), AppointmentEndComboBox.getValue());
        int cId = AppointmentCustomerIDComboBox.getValue().getCustomerID();

        for(Appointment appointment : allAppoints){
            System.out.print(appointment.getAppointmentStart());
            System.out.println("\n end of appt " + aEnd);
            if (appointment.getAppointmentCustId() == cId) {
                if(aStart.isEqual(appointment.getAppointmentStart())){
                    isNotOverlapping = false;
                    System.out.println("atStart");
                }
                if (aStart.isAfter(appointment.getAppointmentStart()) && aStart.isBefore(appointment.getAppointmentEnd())){
                    isNotOverlapping = false;
                    System.out.println("checking between");
                }
                if(appointment.getAppointmentStart().isBefore(aEnd) && appointment.getAppointmentEnd().isBefore(aEnd)){
                    isNotOverlapping = false;
                    System.out.println("end overlaps with start");
                }
                if(aStart.isBefore(appointment.getAppointmentStart()) && aEnd.isAfter(appointment.getAppointmentEnd())){
                    isNotOverlapping = false;
                    System.out.println("This surrounds an existing appointment");
                }
            } else {
                isNotOverlapping = true;
            }
        }
        System.out.println(isNotOverlapping);
        return isNotOverlapping;
    }

    /**
     * returns appointment Date from appointmentDate Picker
     * @return AppointmentDatePicker
     */
    public LocalDate appointmentDate(){
        return AppointmentDatePicker.getValue();
    }

    /**
     * returns appointment StartDate Time
     * @param date
     * @param time
     * @return appointmentStartDateTime
     */
    public LocalDateTime getAppointmentStartDateTime(LocalDate date, LocalTime time){
        appointmentStartDateTime = LocalDateTime.of(date, time);

        return appointmentStartDateTime;
    }

    /**
     * Method returns appointment end date time
     * @param date
     * @param time
     * @return AppointmentEndDateTime
     */
    public LocalDateTime getAppointmentEndDateTime(LocalDate date, LocalTime time){
        appointmentEndDateTime = LocalDateTime.of(date, time);

        return appointmentEndDateTime;
    }
}
