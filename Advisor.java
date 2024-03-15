import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User {
    private ArrayList<UUID> students;
    private boolean isAdmin;
    
    //new Advisor constructor
    public Advisor(String firstName, String lastName, String email, boolean isAdmin, String password) {
        super(firstName, lastName, email, password);
        students = new ArrayList<UUID>();
        this.isAdmin = isAdmin;
    }

    //loading from JSON files constructor
    public Advisor(String firstName, String lastName, String email, UUID id, ArrayList<UUID> students, boolean isAdmin, String password) {
        super(firstName, lastName, email, id, password);
        this.students = students;
        this.isAdmin = isAdmin;
    }

    public Student searchByStudentID(UUID id) {
        for (UUID uuid : students) {
            if(uuid.equals(id)) {
                return UserList.getInstance().getStudentFromID(id);
            }
        }
        return null;
    }

    public void addCourseForStudent(Student student, String course) {
        student.addCourse(course);
    }

    public void removeCourseForStudent(Student student, String course) {
        student.removeCourse(course);
    }

    public void addNotes(Student student, String notes) {
        student.addNotes(notes);
    }

    public void enterFailureRisk(Student student) {
        student.editFailureRisk(true);
    }

    public void removeFailureRisk(Student student) {
        student.editFailureRisk(false);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public ArrayList<UUID> getStudents() {
        return students;
    }

    public String toString() {
        String out = super.toString() + "\n";
        out += "Advisor" + (isAdmin? " and Admin" : "") + "\n";
        out += "Students: " + students.toString();
        return out;
    }
}
