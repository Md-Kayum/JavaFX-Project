package pkg223061008_project.techmentor;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class Edit_userController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField roleField;

    private Manage_usersController.User user;
    private Runnable refreshCallback;

    public void setUser(Manage_usersController.User user, Runnable refreshCallback) {
        this.user = user;
        this.refreshCallback = refreshCallback;

        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        roleField.setText(user.getRole());
    }

    @FXML
    private void handleSave() {
        String name = nameField.getText();
        String email = emailField.getText();
        String role = roleField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechmentorFX", "root", "1234")) {
            String query = "UPDATE users SET name = ?, email = ?, role = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, role);
            stmt.setInt(4, user.getId());

            stmt.executeUpdate();

            if (refreshCallback != null) {
                refreshCallback.run(); // Refresh main table
            }

            ((Stage) nameField.getScene().getWindow()).close(); // Close popup
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        ((Stage) nameField.getScene().getWindow()).close();
    }
}
