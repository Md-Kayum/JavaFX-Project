/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg223061008_project.techmentor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Apollo Gadget
 */
public class LoginController implements Initializable {

    @FXML
    private TextField emailfield;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private Hyperlink registerLink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlelogin(ActionEvent event) {
        String email = emailfield.getText();
    String password = passwordfield.getText();

    if (email.isEmpty() || password.isEmpty()) {
        showAlert("Please enter both email and password.");
    } else {
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        showAlert("Login successful!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Course_catalouge.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("TechMentor - Courses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    private void showAlert(String message) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    alert.setTitle("Login Message");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void goToRegister(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("TechMentor - Register");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    }
    

