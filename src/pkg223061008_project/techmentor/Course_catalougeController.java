package pkg223061008_project.techmentor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Course_catalougeController implements Initializable {

    @FXML
    private Button EnrollButtonNet;
    @FXML
    private Button EnrollButtonHTML;
    @FXML
    private Button EnrollButtonCyber;
    @FXML
    private Button EnrollButtonpyth;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Any future initialization logic goes here
    }

    @FXML
    private void HandleHTMLEnroll(ActionEvent event) {
        showEnrollConfirmation("HTML Basics");
    }

    @FXML
    private void HandlePytEnroll(ActionEvent event) {
        showEnrollConfirmation("Python for Beginners");
    }

    @FXML
    private void handleEnrollNet(ActionEvent event) {
        showEnrollConfirmation("Networking Fundamentals");
    }

    @FXML
    private void HandlecyberEnroll(ActionEvent event) {
        showEnrollConfirmation("Introduction to Cybersecurity");
    }

    private void showEnrollConfirmation(String courseName) {
        System.out.println("Enrolled in: " + courseName);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Enrollment Successful");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully enrolled in: " + courseName);
        alert.showAndWait();

        
    }
}
