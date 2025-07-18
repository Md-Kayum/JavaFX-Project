package pkg223061008_project.techmentor;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class DashboardController implements Initializable {

    @FXML
    private PieChart userDistributionChart;

    @FXML
    private javafx.scene.control.Label userCountLabel;

    @FXML
    private javafx.scene.control.Label courseCountLabel;

    @FXML
    private javafx.scene.control.Label adminCountLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUserCount();
        loadCourseCount();
        loadAdminCount();
        loadEnrollmentsPieChart();
    }

    private void loadUserCount() {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT COUNT(*) AS total FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userCountLabel.setText(String.valueOf(rs.getInt("total")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseCount() {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT COUNT(*) AS total FROM courses";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                courseCountLabel.setText(String.valueOf(rs.getInt("total")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAdminCount() {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT COUNT(*) AS total FROM users WHERE role = 'admin'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                adminCountLabel.setText(String.valueOf(rs.getInt("total")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEnrollmentsPieChart() {
        try (Connection conn = Database.getConnection()) {
            String sql = """
                SELECT course_name, COUNT(*) AS enrollment_count
                FROM enrollments
                GROUP BY course_name
                ORDER BY enrollment_count DESC
                LIMIT 10
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                int count = rs.getInt("enrollment_count");
                pieChartData.add(new PieChart.Data(courseName, count));
            }

            userDistributionChart.setData(pieChartData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
