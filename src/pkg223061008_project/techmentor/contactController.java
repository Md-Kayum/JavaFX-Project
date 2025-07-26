package pkg223061008_project.techmentor;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class contactController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextArea messageArea;

    private final String DB_URL = "jdbc:mysql://localhost:3306/TechmentorFX";
    private final String DB_USER = "root";
    private final String DB_PASS = "1234";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No initialization needed for now
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String subject = subjectField.getText().trim();
        String message = messageArea.getText().trim();

       
        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Name, Email, and Message are required.");
            return;
        }

        
        String insertSQL = "INSERT INTO messages (name, email, subject, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, subject.isEmpty() ? null : subject);
            pstmt.setString(4, message);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Your message has been sent. Thank you!");
                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to send message. Please try again.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not connect to the database or execute the query.");
        }
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        subjectField.clear();
        messageArea.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
