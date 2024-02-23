import java.util.UUID;

public class Advisor extends User {
    private ArrayList<Student> students;
    private boolean isAdmin;
    // Full Constructor
    public Advisor(String firstName, String lastName, String email, ArrayList<Student> students, boolean isAdmin) {
        super(firstName, lastName, email);
        this.students = students;
        this.isAdmin = isAdmin;
    }
    //Constructor without Students
    public Advisor(String firstName, String lastName, String email, UUID id, boolean isAdmin) {
        super(firstName, lastName, email, id);
        this.students = new ArrayList<Student>();
        this.isAdmin = isAdmin;
    }
    //Basic Constructor
    public Advisor(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        this.students = new ArrayList<Student>();
        this.isAdmin = false;
    }
    //Basic Constructor with ID
    public Advisor(String firstName, String lastName, String email, UUID id) {
        super(firstName, lastName, email, id);
        this.students = new ArrayList<Student>();
        this.isAdmin = false;
    }

    public Student searchByStudentID(String id) {
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
}
