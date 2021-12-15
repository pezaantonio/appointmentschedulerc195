package controller;

import DAO.DatabaseConnection;
import DAO.DateTime;
import DAO.UserDao;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
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
    @FXML
    private Label ZoneIdLabel;
    @FXML
    private Label LoginErrorLabel;
    @FXML
    public int userIDFromUsername;

    protected ZoneId localZoneId = ZoneId.systemDefault();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("properties/login", Locale.getDefault());
            UsernameLabel.setText(rb.getString("username"));
            UsernameTextField.setPromptText(rb.getString("username"));
            PasswordLabel.setText(rb.getString("password"));
            PasswordTextField.setPromptText(rb.getString("password"));
            LoginButton.setText(rb.getString("signin"));
            ZoneIdLabel.setText(localZoneId.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    /**
     * Method to handle Login button
     */
    private void LoginButtonHandler(ActionEvent actionEvent) throws SQLException, IOException {
        String usernameInput = UsernameTextField.getText();
        String passwordInput = PasswordTextField.getText();

        if (UserDao.checkUser(usernameInput, passwordInput)) {
            loginLoggerSuccess(usernameInput);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/usermain.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            loginLoggerFail(usernameInput);
            ResourceBundle rb = ResourceBundle.getBundle("properties/login", Locale.getDefault());
            LoginErrorLabel.setText(rb.getString("incorrect"));
        }

    }

    /**
     * Method to add logged in user to a log file
     * @param user
     */
    private void loginLoggerSuccess(String user) {
        try{
            String logFile = "login_activity.txt";
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(logFile, true));
            bWriter.append(DateTime.getTimeStamp() + " User: " + user + " - Successful" + "\n");
            System.out.println("login recorded");
            bWriter.flush();
            bWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Method to add logged in user to a log file if fail
     * @param user
     */
    private void loginLoggerFail(String user) {
        try{
            String logFile = "login_activity.txt";
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(logFile, true));
            bWriter.append(DateTime.getTimeStamp() + " User: " + user + " - Unsuccessful" + "\n");
            System.out.println("login recorded");
            bWriter.flush();
            bWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
