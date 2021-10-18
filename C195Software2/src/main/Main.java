package main;

import DAO.DatabaseConnection;
import DAO.DatabaseQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        DatabaseConnection.openConnection();
        launch(args);
        DatabaseConnection.closeConnection();
    }
}
