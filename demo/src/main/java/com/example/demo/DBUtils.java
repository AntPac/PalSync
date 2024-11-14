package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import com.example.demo.MakeEventController;


import java.sql.*;
import java.io.IOException;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();

            // Check and set the controller if necessary
            if (username != null) {
                Object controller = loader.getController();
                if (controller instanceof LoggedInController) {
                    ((LoggedInController) controller).setUserInformation(username);
                } else if (controller instanceof MakeEventController) {
                    ((MakeEventController) controller).setUsername(username);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the stage properly
        Stage stage;
        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            // This handles cases when the source is not a Node (like MenuItem)
            stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        }

        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400)); // Adjust scene size if needed
        stage.show();
    }



    public static void signUpUser(ActionEvent event, String username, String password) {

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");

            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username is already taken.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (? , ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "calendarPage.fxml", "Welcome!", username);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            // Used to free up resources once action is finished.
            // We will close all previous vars from above.

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PalSyncDB", "root", "AugChico");

            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {

                // Loop that iterates through our database checking to see if we
                // have a password that matches with a username.

                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "calendarPage.fxml", "Welcome!", username);
                    } else {
                        System.out.println("Passwords don't match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password is incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}




