package pkg223061008_project.techmentor;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class myenrollmentController implements Initializable {

    @FXML
    private VBox courseListContainer;
    @FXML
    private Button dashboardId;
    @FXML
    private Button logoutbutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadEnrolledCourses();
    }

    private void loadEnrolledCourses() {
        String sql = """
            SELECT c.id, c.course_name, c.description, c.instructor, c.created_at
            FROM enrollments e
            JOIN courses c ON e.course_name = c.course_name
            WHERE e.user_id = ?
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Session.loggedInUserId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("description"),
                    rs.getString("instructor"),
                    rs.getString("created_at")
                );

                loadCourseCard(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCourseCard(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EnrollmentCard.fxml"));
            AnchorPane card = loader.load();

            EnrollmentCardController controller = loader.getController();
            controller.setCourseData(course);

            courseListContainer.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void HandleLogout(ActionEvent event) {
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

    

    @FXML
    private void Handlebacktodas(ActionEvent event) {

    try {
        Parent root = FXMLLoader.load(getClass().getResource("Course_catalouge.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("TechMentor - Login");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    }

