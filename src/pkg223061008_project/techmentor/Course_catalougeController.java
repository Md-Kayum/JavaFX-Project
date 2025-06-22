/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pkg223061008_project.techmentor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Apollo Gadget
 */
public class Course_catalougeController implements Initializable {

    @FXML
    private Button enrollButton;
    @FXML
    private Button enrollButton1;
    @FXML
    private Button enrollButton11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnrollHTML(ActionEvent event) {
            showEnrollConfirmation("HTML Basics");

    }

    @FXML
    private void EnrollPython(ActionEvent event) {
            showEnrollConfirmation("HTML Basics");

    }

    @FXML
    private void EnrollNet(ActionEvent event) {
            showEnrollConfirmation("HTML Basics");

    }
    private void showEnrollConfirmation(String courseName) {
    System.out.println("Enrolled in: " + courseName);

    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    alert.setTitle("Enrollment Successful");
    alert.setHeaderText(null);
    alert.setContentText("You have successfully enrolled in: " + courseName);
    alert.showAndWait();
}

}
