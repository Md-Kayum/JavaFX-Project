package pkg223061008_project.techmentor;

public class Course {
    private int id;
    private String courseName;
    private String category;      
    private String description;
    private String instructor;
    private String createdAt;
    private String status;        

    // Constructor
    public Course(int id, String courseName, String description, String instructor, String createdAt) {
    this.id = id;
    this.courseName = courseName;
    this.description = description;
    this.instructor = instructor;
    this.createdAt = createdAt;
}


    // Getters
    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    // Setters (if needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
