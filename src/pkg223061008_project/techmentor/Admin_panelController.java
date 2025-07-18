package pkg223061008_project.techmentor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        // TODO: Load Manage Users view
        System.out.println("Manage Users button clicked");
    }

    @FXML
    private void handleManageCoursesButton() {
        // TODO: Load Manage Courses view
        System.out.println("Manage Courses button clicked");
    }

    @FXML
    private void handleReportsButton() {
        // TODO: Load Reports view
        System.out.println("Reports button clicked");
    }

    @FXML
    private void handleLogoutButton() {
        // TODO: Implement logout logic (e.g. go back to login screen)
        System.out.println("Logout button clicked");
    }
}
