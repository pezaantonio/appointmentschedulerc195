package controller;

import DAO.DatabaseConnection;
import DAO.DateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField UsernameTextField;
    @FXML
    private Label UsernameLabel;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private Label PasswordLabel;
    @FXML
    private Button LoginButton;
    //ObservableList<Appointment> appointmentReminderOL = FXCollections.observableArrayList();
    //private DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    //private ZoneId localZoneId = ZoneId.systemDefault();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("properties.login", Locale.getDefault());
            UsernameLabel.setText(rb.getString("username"));
            UsernameTextField.setPromptText(rb.getString("username"));
            PasswordLabel.setText(rb.getString("password"));
            PasswordTextField.setPromptText(rb.getString("password"));
            LoginButton.setText(rb.getString("signin"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void LoginButtonHandler(ActionEvent actionEvent) throws SQLException, IOException {
        String usernameInput = UsernameTextField.getText();
        String passwordInput = PasswordTextField.getText();

        if (checkUser(usernameInput, passwordInput)) {
            loginLogger(usernameInput);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/usermain.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Error");
            alert.setHeaderText("Incorrect Username and/or Password");
            alert.setContentText("Enter valid Username and Password");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    private boolean checkUser(String username, String password) throws SQLException {
        Statement statement = DatabaseConnection.connection.createStatement();
        String sqlQuery = "SELECT User_Name, Password FROM users WHERE User_Name = '" + username + "'";
        ResultSet result = statement.executeQuery(sqlQuery);

        while(result.next()){
            if(result.getString("Password").equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
    *
    * */
    private void loginLogger(String user) {
        try{
            String logFile = "loginlogs.txt";
            PrintWriter pWriter = new PrintWriter(logFile);
            pWriter.append(DateTime.getTimeStamp() + " " + user + " " + "\n");
            System.out.println("New login recorded in log file.");
            pWriter.flush();
            pWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
