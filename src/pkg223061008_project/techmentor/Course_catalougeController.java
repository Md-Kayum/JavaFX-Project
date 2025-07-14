package pkg223061008_project.techmentor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pkg223061008_project.techmentor.Session;

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
       if (isAlreadyEnrolled("Networking Fundamentals")) {
        EnrollButtonNet.setDisable(true);
        EnrollButtonNet.setText("Enrolled");
    }
    if (isAlreadyEnrolled("HTML Basics")) {
        EnrollButtonHTML.setDisable(true);
        EnrollButtonHTML.setText("Enrolled");
    }
    if (isAlreadyEnrolled("Python for Beginners")) {
        EnrollButtonpyth.setDisable(true);
        EnrollButtonpyth.setText("Enrolled");
    }
    if (isAlreadyEnrolled("Introduction to Cybersecurity")) {
        EnrollButtonCyber.setDisable(true);
        EnrollButtonCyber.setText("Enrolled");
    }
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
   saveEnrollment(courseName);  // Save enrollment to DB

    System.out.println("Enrolled in: " + courseName);

    // Disable the corresponding button
    switch (courseName) {
        case "Networking Fundamentals":
            EnrollButtonNet.setDisable(true);
            EnrollButtonNet.setText("Enrolled");
            break;
        case "HTML Basics":
            EnrollButtonHTML.setDisable(true);
            EnrollButtonHTML.setText("Enrolled");
            break;
        case "Python for Beginners":
            EnrollButtonpyth.setDisable(true);
            EnrollButtonpyth.setText("Enrolled");
            break;
        case "Introduction to Cybersecurity":
            EnrollButtonCyber.setDisable(true);
            EnrollButtonCyber.setText("Enrolled");
            break;
    }

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Enrollment Successful");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully enrolled in: " + courseName);
    alert.showAndWait();
}
    private void saveEnrollment(String courseName) {
    try {
        Connection conn = Database.getConnection();
        String query = "INSERT INTO enrollments (user_id, course_name) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, Session.loggedInUserId);
        ps.setString(2, courseName);
        int rows = ps.executeUpdate();
        System.out.println("Rows inserted: " + rows);
        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private boolean isAlreadyEnrolled(String courseName) {
    try {
        Connection conn = Database.getConnection();
        String query = "SELECT * FROM enrollments WHERE user_id = ? AND course_name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, Session.loggedInUserId);
        ps.setString(2, courseName);
        boolean enrolled = ps.executeQuery().next();
        ps.close();
        conn.close();
        return enrolled;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    @FXML
    private void HandleLogout(ActionEvent event) {
        // Reset session
    Session.loggedInUserId = -1;

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
