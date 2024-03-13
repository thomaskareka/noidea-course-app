//#21
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userlist;
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;

    private UserList() {
        students = DataLoader.getStudents();
        advisors = DataLoader.getAdvisors();
    }
    public static UserList getInstance(){
        if (userlist == null) {
            userlist = new UserList();
        }
        return userlist;
    }
    
    public User login(String email, String password){
        for (Advisor advisor : advisors) {
            if(advisor.getEmail().equals(email) && advisor.getPassword().equals(password))
                return advisor;
        }
        for (Student student : students) {
            if(student.getEmail().equals(email) && student.getPassword().equals(password))
                return student;
        }
        
        return null;
    }
    
    public boolean signUp(boolean type, String firstName, String lastName, String email, String password){
        if(!containsUser(email)){
            // the boolean 'type' will be true if the user signing up is a student, and false if an advisor.
            // all information that is not held in the User class will have to be inputted later in, maybe, an update profile method.
            if(type){
                addStudentUser(lastName, lastName, email, null, password);
                return true;
            }
            else{
                addAdvisorUser(firstName, lastName, email, false, password);
                return true;
            }
        }
        return false;
    }


    public Student getStudentByEmail(String email) {
        for (Student student : students) {
            if(student.getEmail().equals(email))
                return student;
        }
        return null;
    }

    public Student getStudentFromID(UUID id) {
        for (Student student : students) {
            if(student.getID().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public String createUserTranscript(UUID id){
       Student holder = getStudentFromID(id);
        if(holder != null){
          return holder.getTranscript();  
        }
        return "";
    }

    public String getAllStudentCourses(UUID id){
        Student holder = getStudentFromID(id);
        if(holder != null){
            return holder.getAllCourses();
        }
        return "";
    }

    public Advisor getAdvisorByEmail(String email){
        for (Advisor advisor : advisors) {
            if(advisor.getEmail().equals(email))
                return advisor;
        }
        return null;
    }

    public Advisor getAdvisorFromID(UUID id) {
        for (Advisor advisor : advisors) {
            if(advisor.getID() == id) {
                return advisor;
            }
        }
        return null;
    }

    private boolean containsUser(String email){
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

    public String getStudentIncompleteCourses(Student student){
        return student.getAllIncompletedCourses();
    }

    public String getStudentCompleteCourses(Student student){
        return student.getAllCompletedCourses();
    }

    public boolean checkIfStudentIsAtRisk(Student student){
        return student.checkIfAtRisk();
    }

    public double studentOverallGPA(Student student){
        return student.getOverallGPA();
    }

    public double studentMajorGPA(Student student){
        return student.getMajorGPA();
    }

    public void addStudentUser(String fisrtName, String lastName, String email, String major, String password){       
        Student student = new Student(fisrtName, lastName, email, major, password);
        students.add(student);
    }

    public void addAdvisorUser(String firstName, String lastName, String email, boolean isAdmin, String password){
        Advisor advisor = new Advisor(firstName, lastName, email, isAdmin, password);
        advisors.add(advisor);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }
    
}
