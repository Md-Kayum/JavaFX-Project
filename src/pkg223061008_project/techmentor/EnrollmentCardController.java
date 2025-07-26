package pkg223061008_project.techmentor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EnrollmentCardController {

    @FXML
    private Label courseTitle;

    @FXML
    private Label courseDesc;

    @FXML
    private Label courseInstructor;

    @FXML
    private Label progressLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label nextSession;

    @FXML
    private Button certButton;

    @FXML
    private Button continueBtn;

    @FXML
    private Button dropBtn;

    @FXML
    private ImageView courseImage;
    @FXML
    private Course course;


   
    public void setCourseData(Course course) {
        
        this.course = course;
        courseTitle.setText("Course Title: " + course.getCourseName());
        courseDesc.setText(course.getDescription());
        courseInstructor.setText("Instructor: " + course.getInstructor());

       
        double progress = Math.random();
        progressBar.setProgress(progress);
        progressLabel.setText("Progress: " + (int) (progress * 100) + "%");

       
        nextSession.setText("Next Session: Aug 2, 10:00 AM");

      
        certButton.setVisible(progress >= 1.0);

       
        String courseName = course.getCourseName().toLowerCase();
        String imageFile = "default_course.png";

        if (courseName.contains("networking")) {
            imageFile = "networking.jpg";
        } else if (courseName.contains("html")) {
            imageFile = "Web bootcamp.jpg";
        } else if (courseName.contains("python")) {
            imageFile = "python lang.jpg";
        } else if (courseName.contains("cyber")) {
            imageFile = "cybers.jpg";
        }

        try {
            Image img = new Image(getClass().getResourceAsStream(
                    "/pkg223061008_project/techmentor/images/" + imageFile));
            courseImage.setImage(img);
        } catch (Exception e) {
            System.out.println("Image not found: " + imageFile + " — using no image.");
        }
    }

    @FXML
private void handleContinue() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseDetail.fxml"));
        Parent root = loader.load();

        CourseDetailController controller = loader.getController();
        controller.setCourse(this.course);

        Stage stage = (Stage) continueBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Learning - " + course.getCourseName());
        stage.setMaximized(true);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @FXML
    private void handleDrop() {
        System.out.println("Drop clicked for: " + courseTitle.getText());
        // TODO: drop course logic
    }

    @FXML
    private void handleCertificate() {
        System.out.println("Certificate clicked for: " + courseTitle.getText());
        // TODO: open certificate PDF
    }
}
