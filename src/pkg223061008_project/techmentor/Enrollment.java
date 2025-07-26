package pkg223061008_project.techmentor;
import java.time.LocalDate;

public class Enrollment {
    private String courseName;
    private String description;
    private String instructor;
    private LocalDate enrollmentDate;

    public Enrollment(String courseName, String description, String instructor, LocalDate enrollmentDate) {
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructor() {
        return instructor;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
}
