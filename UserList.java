//#21
import java.util.ArrayList;

public class UserList {
    private static UserList userlist;
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;

    private UserList(){
        students = new ArrayList<Student>();
        advisors = new ArrayList<Advisor>();
    }
    public UserList getInstance(){
        if (userlist == null) {
            userlist = new UserList();
        }
        return userlist;
    }
    public Student getStudentUser(String email) {
        for (Student student : students) {
            if(student.getEmail().equals(email))
                return student;
        }
        return null;
    }

    public Advisor getAdvisorUser(String email){
        for (Advisor advisor : advisors) {
            if(advisor.getEmail().equals(email))
                return advisor;
        }
        return null;
    }

    public boolean containsUser(String email){
        for (Student student : students) {
            if(student.getEmail().equals(email))
                return true;
        }
        for (Advisor advisor : advisors) {
            if(advisor.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public void addStudentUser(String fisrtName, String lastName, String email, String major){       
        Student student = new Student(fisrtName, lastName, email, major);
        students.add(student);
    }

    public void addAdvisorUser(String firstName, String lastName, String email, boolean isAdmin){
        Advisor advisor = new Advisor(firstName, lastName, email, isAdmin);
        advisors.add(advisor);
    }
    
}
