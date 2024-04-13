package model;
//#21
import java.util.ArrayList;
import java.util.List;
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
        for (Student student : students) {
            if(student.getEmail().toUpperCase().equals(email.toUpperCase()) && student.getPassword().equals(password))
                return student;
        }
        for (Advisor advisor : advisors) {
            if(advisor.getEmail().toUpperCase().equals(email.toUpperCase()) && advisor.getPassword().equals(password))
                return advisor;
        }
        
        return null;
    }
    
    public User signUp(boolean type, String firstName, String lastName, String email, String password){
        if(firstName == null || lastName == null || email == null || password == null || firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("")){
            System.out.println("Input was invalid. Please re-enter information.");
            return null;
        }
        else if(!containsUser(email)){
            // the boolean 'type' will be true if the user signing up is a student, and false if an advisor.
            // all information that is not held in the User class will have to be inputted later in, maybe, an update profile method.
            if(type){
                return addStudentUser(firstName, lastName, email, new String(), password);
            }
            else{
                return addAdvisorUser(firstName, lastName, email, false, password);
            }
        }
        System.out.println("User already exists, attempting to sign in.");
        return login(email, password);
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

    public Student getStudentFromStudentID(String id) {
        for (Student student : students) {
            if(student.getStudentID().equals(id)) {
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
            if(student.getEmail().toUpperCase().equals(email.toUpperCase()))
                return true;
        }
        for (Advisor advisor : advisors) {
            if(advisor.getEmail().toUpperCase().equals(email.toUpperCase()))
                return true;
        }
        return false;
    }

    public boolean addCourseForStudent(Student student, String course){
        return student.addCourse(course);
    }

    public boolean removeCourseForStudent(Student student, Course course){
        return student.removeCourse(course);
    }

    public boolean addGrade(Student student, String course, Grade grade){
        return student.addGrade(course, grade);
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

    public String getCourseGrade(Student student, Course course){
        return student.getCourseGrade(course.getIdentifier());
    }

    public String getCourseGrade(Student student, String identifier){
        return student.getCourseGrade(identifier);
    }

    public double calculateDegreeCompletionPercentage(Student student){
        Degree d = DegreeList.getInstance().getMajor(student.getMajor());
        return student.getDegreePercentage(d);
    }

    public Student addStudentUser(String fisrtName, String lastName, String email, String major, String password){       
        Student student = new Student(fisrtName, lastName, email, major, password);
        students.add(student);
        return student;
    }

    public Advisor  addAdvisorUser(String firstName, String lastName, String email, boolean isAdmin, String password){
        Advisor advisor = new Advisor(firstName, lastName, email, isAdmin, password);
        advisors.add(advisor);
        return advisor;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }
    
    public void saveUser(Student in) {
        for(int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getID().equals(in.getID())) {
                students.set(i, in);
            }
        }
    }

        public void saveUser(Advisor in) {
        for(int i = 0; i < advisors.size(); i++) {
            Advisor s = advisors.get(i);
            if (s.getID().equals(in.getID())) {
                advisors.set(i, in);
            }
        }
    }

    public ArrayList<Student> searchStudentsByName(String search) {
        ArrayList<Student> valid = new ArrayList<>();
        for(Student s: students) {
            if((s.getFirstName().toLowerCase() + " " + s.getLastName().toLowerCase()).contains(search) || s.getStudentID().toLowerCase().contains(search))
                valid.add(s);
        }
        return valid;
    }

    public List<Student> getStudentsFromSearch(int page, String search) {
        if(search.equals("")) {
            return students.subList(Math.min(page * 25, students.size()), Math.min(page * 25 + 24, students.size()));
        }
        ArrayList<Student> valid = searchStudentsByName(search);
        return valid.subList(Math.min(page * 25, valid.size()), Math.min(page * 25 + 24, valid.size()));
    }

    //only for test cases, do not use
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setAdvisors(ArrayList<Advisor> advisors) {
        this.advisors = advisors;
    }
}
