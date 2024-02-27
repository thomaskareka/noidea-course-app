import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User {
    private ArrayList<UUID> students;
    private boolean isAdmin;
    
    //new Advisor constructor
    public Advisor(String firstName, String lastName, String email, boolean isAdmin) {
        super(firstName, lastName, email);
        students = new ArrayList<UUID>();
        this.isAdmin = isAdmin;
    }

    //loading from JSON files constructor
    public Advisor(String firstName, String lastName, String email, UUID id, ArrayList<UUID> students, boolean isAdmin) {
        super(firstName, lastName, email, id);
        this.students = students;
        this.isAdmin = isAdmin;
    }

    public Student searchByStudentID(UUID id) {
        return new Student("first","last", "test@example.com", "Computer Science");
    }

    public Student searchByStudentEmail(String email) {
        return new Student("first", "last", email, "Computer Science");
    }

    public void addCourseForStudent(Student student, Course course) {

    }

    public void moveCourse(Student student, Course course) {

    }

    public void removeCourseForStudent(Student student, Course course) {

    }

    public void addNotes(Student student, String notes) {

    }

    public void enterFailureRisk(Student student, boolean failureRisk) {

    }

    public void removeFailureRisk(Student student, boolean failureRisk) {

    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public ArrayList<UUID> getStudents() {
        return students;
    }
}