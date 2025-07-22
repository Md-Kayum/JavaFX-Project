package pkg223061008_project.techmentor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class Manage_usersController {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    private TableColumn<User, String> genderColumn;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private TableColumn<User, Void> actionColumn;

    @FXML
    private TextField searchField;

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));       // admin/customer
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));   // Male/Female/etc.
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));   // active/inactive

        loadUsersFromDatabase();
        userTable.setItems(userList);
        addActionButtons();
    }

    private void loadUsersFromDatabase() {
        userList.clear();

        String url = "jdbc:mysql://localhost:3306/TechmentorFX";
        String user = "root";
        String password = "1234";

        String query = "SELECT id, name, email, role, gender, status FROM users";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                String gender = rs.getString("gender");
                String status = rs.getString("status");

                userList.add(new User(id, name, email, role, gender, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not load users from database.");
        }
    }

    private void addActionButtons() {
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = param -> new TableCell<>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox hBox = new HBox(5, editBtn, deleteBtn);

            {
                editBtn.setOnAction(event -> {
                    User selectedUser = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_user.fxml"));
                        Parent root = loader.load();

                        Edit_userController controller = loader.getController();

                        controller.setUser(selectedUser, () -> {
                            loadUsersFromDatabase();
                            userTable.refresh();
                        });

                        Stage stage = new Stage();
                        stage.setTitle("Edit User");
                        stage.setScene(new Scene(root));
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        showAlert("Edit Error", "Could not load the edit user dialog.");
                    }
                });

                deleteBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete " + user.getName() + "?",
                            ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            deleteUserFromDatabase(user.getId());
                            userList.remove(user);
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                }
            }
        };

        actionColumn.setCellFactory(cellFactory);
    }

    private void deleteUserFromDatabase(int userId) {
        String url = "jdbc:mysql://localhost:3306/TechmentorFX";
        String user = "root";
        String password = "1234";

        String deleteEnrollments = "DELETE FROM enrollments WHERE user_id = ?";
        String deleteUser = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteEnrollments)) {
                pstmt1.setInt(1, userId);
                pstmt1.executeUpdate();
            }

            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteUser)) {
                pstmt2.setInt(1, userId);
                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Delete Error", "Could not delete the user. Make sure the user is not referenced in other tables.");
        }
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        ObservableList<User> filteredList = FXCollections.observableArrayList();

        for (User user : userList) {
            if (user.getName().toLowerCase().contains(keyword) ||
                    user.getEmail().toLowerCase().contains(keyword)) {
                filteredList.add(user);
            }
        }

        userTable.setItems(filteredList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // --- Make sure you use the correct User class (not an inner class!) ---
    public static class User {
        private final int id;
        private final String name;
        private final String email;
        private final String role;
        private final String gender;
        private final String status;

        public User(int id, String name, String email, String role, String gender, String status) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.gender = gender;
            this.status = status;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getGender() { return gender; }
        public String getStatus() { return status; }
    }
}
