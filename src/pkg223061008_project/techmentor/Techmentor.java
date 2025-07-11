package pkg223061008_project.techmentor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Techmentor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the login.fxml file
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        // Create scene and show window
        Scene scene = new Scene(root);
        stage.setTitle("TechMentor - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
