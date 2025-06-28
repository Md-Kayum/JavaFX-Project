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

public class LoginController implements Initializable {

    @FXML
    private TextField emailfield;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private Hyperlink registerLink;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code (if needed)
    }    

    @FXML
    private void handlelogin(ActionEvent event) {
        String email = emailfield.getText();
        String password = passwordfield.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Please enter both email and password.");
        } else {
            try {
                java.sql.Connection conn = Database.getConnection();
                String query = "SELECT * FROM users WHERE email = ? AND password = ?";
                java.sql.PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, password);
                java.sql.ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    showAlert("Login successful!");
                    Parent root = FXMLLoader.load(getClass().getResource("Course_catalouge.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("TechMentor - Courses");
                } else {
                    showAlert("Invalid email or password.");
                }

                rs.close();
                ps.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error connecting to database.");
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
