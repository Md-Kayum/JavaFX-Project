package pkg223061008_project.techmentor;

public class User {
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
