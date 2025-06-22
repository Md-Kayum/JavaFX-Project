/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg223061008_project.techmentor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Apollo Gadget
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmpasswordField;
    @FXML
    private RadioButton maleradio;
    @FXML
    private ToggleGroup Group1;
    @FXML
    private RadioButton femaleradio;
    @FXML
    private Button registerButton;
    @FXML
    private Hyperlink loginLink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleregister(ActionEvent event) {
        String name = nameField.getText();
    String email = emailField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmpasswordField.getText();
    String gender = null;

    if (maleradio.isSelected()) {
        gender = "Male";
    } else if (femaleradio.isSelected()) {
        gender = "Female";
    }

    // Basic validation
    if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender == null) {
        showAlert("Registration Error", "Please fill in all fields and select gender.");
        return;
    }

    if (!password.equals(confirmPassword)) {
        showAlert("Password Mismatch", "Passwords do not match.");
        return;
    }

    // Simulate registration success
    System.out.println("Registered User:");
    System.out.println("Name: " + name);
    System.out.println("Email: " + email);
    System.out.println("Gender: " + gender);

    showAlert("Success", "Registration successful!");

    }

    
    
    private void showAlert(String title, String message) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void goToLogin(ActionEvent event) {
        try {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("login.fxml"));
        javafx.scene.Parent root = loader.load();
        javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("TechMentor - Login");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
