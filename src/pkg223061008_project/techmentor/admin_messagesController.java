package pkg223061008_project.techmentor;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class admin_messagesController implements Initializable {

    @FXML
    private TableView<Message> messagesTable;

    @FXML
    private TableColumn<Message, Integer> idColumn;

    @FXML
    private TableColumn<Message, String> nameColumn;

    @FXML
    private TableColumn<Message, String> emailColumn;

    @FXML
    private TableColumn<Message, String> subjectColumn;

    @FXML
    private TableColumn<Message, String> messageColumn;

    @FXML
    private TableColumn<Message, String> dateColumn;

    private final ObservableList<Message> messageList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadMessagesFromDatabase();
        messagesTable.setItems(messageList);
    }

    private void loadMessagesFromDatabase() {
        messageList.clear();

        String dbUrl = "jdbc:mysql://localhost:3306/TechmentorFX";
        String user = "root";
        String password = "1234";
        String query = "SELECT id, name, email, subject, message, date FROM messages";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Message msg = new Message(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("subject"),
                    rs.getString("message"),
                    rs.getString("date")
                );
                messageList.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
}
