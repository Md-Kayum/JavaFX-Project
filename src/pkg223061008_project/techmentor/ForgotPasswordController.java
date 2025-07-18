package pkg223061008_project.techmentor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPasswordController {

    @FXML
    private TextField emailField;
    @FXML
    private Button verifyEmailButton;
    @FXML
    private Label securityQuestionLabel;
    @FXML
    private TextField securityAnswerField;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button resetPasswordButton;
    @FXML
    private Label messageLabel;
    @FXML
    private Hyperlink backtologin;

    private String currentEmail;
    private String currentSecurityQuestion;

    @FXML
    private void initialize() {
        
        setSecurityFieldsVisible(false);
    }

    private void setSecurityFieldsVisible(boolean visible) {
        securityQuestionLabel.setVisible(visible);
        securityAnswerField.setVisible(visible);
        newPasswordLabel.setVisible(visible);
        newPasswordField.setVisible(visible);
        confirmPasswordLabel.setVisible(visible);
        confirmPasswordField.setVisible(visible);
        resetPasswordButton.setVisible(visible);
    }

    @FXML
    private void handleVerifyEmail(ActionEvent event) {
        messageLabel.setText("");
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            messageLabel.setText("Please enter your email.");
            return;
        }

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT security_question FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                currentEmail = email;
                currentSecurityQuestion = rs.getString("security_question");

                securityQuestionLabel.setText("Security Question: " + currentSecurityQuestion);
                setSecurityFieldsVisible(true);

                
                emailField.setDisable(true);
                verifyEmailButton.setDisable(true);

            } else {
                messageLabel.setText("Email not found.");
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Database error occurred.");
        }
    }

    @FXML
private void handleResetPassword(ActionEvent event) {
    messageLabel.setText("");

    String answer = securityAnswerField.getText().trim();
    String newPassword = newPasswordField.getText();
    String confirmPassword = confirmPasswordField.getText();

    if (answer.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
        messageLabel.setText("Please fill all the fields.");
        return;
    }

    if (!newPassword.equals(confirmPassword)) {
        messageLabel.setText("Passwords do not match.");
        return;
    }

    try (Connection conn = Database.getConnection()) {
        String query = "SELECT security_answer FROM users WHERE email = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, currentEmail);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String correctAnswer = rs.getString("security_answer");
            if (!correctAnswer.equalsIgnoreCase(answer)) {
                messageLabel.setText("Incorrect security answer.");
                return;
            }
        } else {
            messageLabel.setText("Unexpected error. Try again.");
            return;
        }

        rs.close();
        ps.close();

        // Update password
        String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, newPassword);
        updateStmt.setString(2, currentEmail);
        updateStmt.executeUpdate();
        updateStmt.close();

        // Show confirmation popup
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Password reset successful! You can now log in.");
        alert.showAndWait();

        // Go to login
        goToLoginPage();
    } catch (Exception e) {
        e.printStackTrace();
        messageLabel.setText("Error resetting password.");
    }
}

    @FXML
    private void handletobacklogin(ActionEvent event) {
        goToLoginPage();
    }

    private void goToLoginPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            
            Stage currentStage = (Stage) emailField.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to return to login.");
        }
    }
}
