package model;
import java.util.ArrayList;
import java.util.List;
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

    public void setFirstName(String name) {
        if(name != null && !(name.isEmpty()))
            this.firstName = name;
    }

    public void setLastName(String name) {
        if(name != null && !(name.isEmpty()))
            this.lastName = name;
    }

    public void setEmail(String email) {
        if(email != null && !(email.isEmpty()))
            this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student searchByStudentID(UUID id) {
        for (UUID uuid : students) {
            if(uuid.equals(id)) {
                return UserList.getInstance().getStudentFromID(id);
            }
        }
        return null;
    }

    public boolean addAdvisee(UUID id) {
        if(students.contains(id)) {
            System.out.println("Student already added!");
            return false;
        } else {
            students.add(id);
            return true;
        }
    }

    private ArrayList<Student> searchStudentsByName(String search, ArrayList<Student> st) {
        ArrayList<Student> valid = new ArrayList<>();
        for(Student s : st) {
            if ((s.getFirstName().toLowerCase() + " " + s.getLastName().toLowerCase()).contains(search) || s.getStudentID().toLowerCase().contains(search))
                valid.add(s);
        }
        return valid;
    }

    public List<Student> searchStudents(int page, String search) {
        ArrayList<Student> st = getStudentObjects();
        if(search.equals("")) {
            return st.subList(Math.min(page * 25, st.size()), Math.min(page * 25 + 24, st.size()));
        }
        ArrayList<Student> valid = searchStudentsByName(search, st);
        return valid.subList(Math.min(page * 25, valid.size()), Math.min(page * 25 + 24, valid.size()));
    }

    public void addCourseForStudent(Student student, String course) {
        student.addCourse(course);
    }

    public void removeCourseForStudent(Student student, Course course) {
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

    public ArrayList<Student> getStudentObjects() {
        ArrayList<Student> out = new ArrayList<>();
        for(UUID u : students) {
            out.add(UserList.getInstance().getStudentFromID(u));
        }
        return out;
    }

    public String toString() {
        String out = super.toString() + "\n";
        out += "Advisor" + (isAdmin? " and Admin" : "") + "\n";
        out += "Students: " + students.toString();
        return out;
    }
}
