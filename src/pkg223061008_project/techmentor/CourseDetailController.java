package pkg223061008_project.techmentor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class CourseDetailController {

    @FXML
    private Label courseTitle;

    @FXML
    private TextArea lessonContent;

    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        courseTitle.setText(course.getCourseName());
        lessonContent.setText(generateLessonText(course.getCourseName()));
    }

    private String generateLessonText(String courseName) {
    courseName = courseName.toLowerCase();

    if (courseName.contains("network")) {
        return """
        Welcome to **Networking Basics**!

        In this course, you'll explore the fundamentals of computer networks. Starting with understanding how devices communicate, we’ll dive into protocols like TCP/IP, DNS, and HTTP. You’ll learn how IP addressing and subnetting work and how routers and switches control traffic.

        🔹 Key Topics:
        - Types of networks (LAN, WAN, MAN)
        - Network devices and their roles
        - OSI and TCP/IP models
        - Packet transmission and routing

        By the end, you'll be able to identify real-world networking setups and troubleshoot common issues.
        """;
    } else if (courseName.contains("html")) {
        return """
        Welcome to **HTML Basics**!

        HTML is the foundation of every web page. In this course, you’ll learn how to build structured documents using semantic tags, create forms, insert media, and control layout with containers.

        🔹 Key Topics:
        - Basic tags: `<html>`, `<head>`, `<body>`, `<div>`
        - Text formatting and images
        - Lists, tables, and links
        - Forms and input types

        This is your first step into the world of web development!
        """;
    } else if (courseName.contains("python")) {
        return """
        Welcome to **Python for Beginners**!

        Python is known for its readability and power. This course introduces the core concepts of programming using Python, including syntax, control structures, data types, and functions.

        🔹 Key Topics:
        - Variables and data types
        - Conditional statements and loops
        - Functions and modules
        - Basic file I/O

        You’ll build simple console programs and gain confidence for more advanced topics like web development or data science.
        """;
    } else if (courseName.contains("cyber")) {
        return """
        Welcome to **Cybersecurity Basics**!

        This course provides an introduction to protecting systems and data from digital attacks. You'll learn how hackers think, what common vulnerabilities exist, and how to defend against them.

        🔹 Key Topics:
        - Threat models and types of cyber attacks
        - Malware, ransomware, phishing
        - Firewalls, VPNs, and antivirus
        - Basic password and authentication hygiene

        After completing this, you’ll understand how to be safer online and begin your journey into ethical hacking or IT security.
        """;
    }

    // Default fallback
    return """
    Welcome to your course!

    Course materials will be available here soon. Stay tuned for updates.
    """;
}

    @FXML
    private void handleMarkComplete() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lesson Complete");
        alert.setHeaderText(null);
        alert.setContentText("Marked as complete! Great job.");
        alert.showAndWait();
    }

    @FXML
    private void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("myenrollment.fxml"));
            Stage stage = (Stage) courseTitle.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("My Enrollments");
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
