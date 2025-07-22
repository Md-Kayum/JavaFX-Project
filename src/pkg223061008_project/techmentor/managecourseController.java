package pkg223061008_project.techmentor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class managecourseController implements Initializable {

    @FXML private TextField searchField;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> idColumn;
    @FXML private TableColumn<Course, String> nameColumn;
    @FXML private TableColumn<Course, String> instructorColumn;
    @FXML private TableColumn<Course, String> createdAtColumn;
    @FXML private TableColumn<Course, Void> actionColumn;
    // Description column optional, add if you want
    // @FXML private TableColumn<Course, String> descriptionColumn;

    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getId()));
        nameColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCourseName()));
        instructorColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getInstructor()));
        createdAtColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCreatedAt()));
        // descriptionColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDescription()));

        loadCoursesFromDatabase();
        courseTable.setItems(courseList);
    }

    private void loadCoursesFromDatabase() {
        courseList.clear();
        String url = "jdbc:mysql://localhost:3306/TechmentorFX";
        String user = "root";
        String password = "1234";
        String query = "SELECT id, course_name, description, instructor, created_at FROM courses";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String courseName = rs.getString("course_name");
                String description = rs.getString("description");
                String instructor = rs.getString("instructor");
                String createdAt = rs.getString("created_at");

                Course course = new Course(id, courseName, description, instructor, createdAt);
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not load courses from database.");
        }
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        ObservableList<Course> filteredList = FXCollections.observableArrayList();
        for (Course course : courseList) {
            if ((course.getCourseName() != null && course.getCourseName().toLowerCase().contains(keyword)) ||
                (course.getInstructor() != null && course.getInstructor().toLowerCase().contains(keyword))) {
                filteredList.add(course);
            }
        }
        courseTable.setItems(filteredList);
    }

    @FXML
    private void handleAddCourse() {
        // TODO: Show add dialog
        showAlert("Not Implemented", "Add Course feature is not yet implemented.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
