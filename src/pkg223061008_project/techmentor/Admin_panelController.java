package pkg223061008_project.techmentor;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin_panelController {

    @FXML
    private VBox sidebar;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private void initialize() {
        // Load dashboard view by default on admin panel startup
        loadDashboard();
    }

    @FXML
    private void handleDashboardButton() {
        loadDashboard();
    }

    private void loadDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            mainContent.getChildren().clear();
            mainContent.getChildren().add(root);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
private void handleManageUsersButton() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("manage_users.fxml"));
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   @FXML
private void handleManageCoursesButton() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("manage_course.fxml")); // Or "manage_courses.fxml" if that's your filename
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void handleReportsButton() {
            loadDashboard();

    }

  @FXML
private void handleLogoutButton() {
    try {
        // Load login.fxml
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        // Create a new stage (window) for the login screen
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Login");
        loginStage.show();

        // Close the current admin panel window
        Stage currentStage = (Stage) sidebar.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
