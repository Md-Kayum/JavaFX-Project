package pkg223061008_project.techmentor;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    @FXML
    private ComboBox<String> securityQuestionCombo;
    @FXML
    private TextField securityAnswerField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        securityQuestionCombo.getItems().addAll(
            "What is your pet's name?",
            "What is your mother's maiden name?",
            "What is your favorite color?",
            "What was your first school's name?"
        );
    }

    @FXML
    private void handleregister(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmpasswordField.getText();
        String gender = null;
        String securityQuestion = securityQuestionCombo.getValue();
        String securityAnswer = securityAnswerField.getText();

        if (maleradio.isSelected()) {
            gender = "Male";
        } else if (femaleradio.isSelected()) {
            gender = "Female";
        }

        // Basic validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                gender == null || securityQuestion == null || securityAnswer.isEmpty()) {
            showAlert("Registration Error", "Please fill in all fields, including security question.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Password Mismatch", "Passwords do not match.");
            return;
        }

        try {
            Connection conn = Database.getConnection();

            // Check if email already exists
            String checkQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                showAlert("Registration Error", "Email already registered.");
                rs.close();
                checkStmt.close();
                conn.close();
                return;
            }

            // Insert user including security question & answer
            String insertQuery = "INSERT INTO users (name, email, password, gender, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, password);
            insertStmt.setString(4, gender);
            insertStmt.setString(5, securityQuestion);
            insertStmt.setString(6, securityAnswer);

            insertStmt.executeUpdate();
            insertStmt.close();
            conn.close();

            showAlert("Success", "Registration successful!");

            // Redirect to login
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("TechMentor - Login");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", "Something went wrong.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("TechMentor - Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
