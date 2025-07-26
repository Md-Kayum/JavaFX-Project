package pkg223061008_project.techmentor;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Admin_panelController {

    @FXML
    private VBox sidebar;

    @FXML
    private AnchorPane mainContent;
    @FXML
    private ImageView logoImageView;

   @FXML
void initialize() {
    loadDashboard();
}

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
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Login");
        loginStage.show();

        
        Stage currentStage = (Stage) sidebar.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
private void handlemessage(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_messages.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Admin - Messages");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        
    }
}



}
