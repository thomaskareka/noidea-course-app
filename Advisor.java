import java.util.ArrayList;

public class Advisor {
    private ArrayList<Student> students; 
    private boolean isAdmin;

    public Advisor(String firstName, String lastName, String email, ArrayList<Student> students, ArrayList<Student> failureRiskStudents) {

    }
    public Advisor(String firstName, String lastName, String email, UUID id, ArrayList<Student> students, ArrayList<Student> failureRiskStudents) {

    }
    public Student searchByStudentID(String id) {

    }
    public Student searchByStudentEmail(String email) {

    }
    public addCourseForStudent(Student student, Course course) {

    }
    public void moveCourse(Student student, Course course) {

    }
    public void addNotes(Student student, String notes) {

    }
    public void enterFailureRisk(Student student, boolean failureRisk) {

    }
    public void removeFailureRisk(Student student, boolean failureRisk) {

    }
    public Student getStudent(String name) {

    }
    
}
