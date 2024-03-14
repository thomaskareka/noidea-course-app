import java.util.ArrayList;
import java.util.UUID;

public class CourseSystem{
    private UserList userList;
    private CourseList courseList;
    private DegreeList degreeList;
    private User user;

    public CourseSystem() {
        userList = UserList.getInstance();
        courseList = CourseList.getInstance();
        degreeList = DegreeList.getInstance();
    }
    public boolean login (String email, String password){
       /* This method will call userList's login method and update the current user to be the person who logged in.
        If user is not null then the person successfully logged in */
        user = userList.login(email, password);
       if(user != null)
            return true;
       return false;
    }
    public boolean logout(){
        userList.logout(user);
        user = null;
        return true;
    }
    public boolean signUp(boolean type, String firstName, String lastName, String email, String password){
        /* the boolean 'type' will be true if the user signing up is a student, and false if an advisor.
         All information that is not held in the User class will have to be inputted later in, maybe, an update profile method.*/
        return userList.signUp(type, firstName, lastName, email, password);
    }
    public String showCourseByCode(String identifier){
        return courseList.getCourseByIdentifer(identifier).toString();
    } 
    public String showAllCourses(){
       return courseList.getAllCourses();
    }
    // has to be overwritten so advisor can get a student's classes
    public String getAllUserClasses(){ 
        return userList.getAllStudentCourses(user.getID());
    }
    // has to be overwritten so advisor can get a student's classes
    public String createUserTranscript(){
        return userList.createUserTranscript(user.getID());
    }
    public String courseDescriptionSearchByName(String name){
        return courseList.getCourseDescriptionByName(name);
    }
    public String courseDescriptionSearchByIdentifier(String identifier){
        return courseList.getCourseDescriptionByIdentifer(identifier);
    }
    public String courseRequistesSearchByName(String name){
        return courseList.getReqsByName(name);
    }
    public String courseRequistesSearchByIdentifer(String identfier){
        return courseList.getReqsByIdentifer(identfier);
    }
    public boolean addCourse(Advisor advisor, String name, String identifier, int credits, String description, ArrayList<String> attributes, ArrayList<Requisite> requisite, String reqText){
        return courseList.addCourse(advisor, name, identifier, credits, description, attributes,  requisite, reqText);
    }
    public boolean addCourse(Advisor advisor, String name, String identifier, int credits){
       return courseList.addCourse(advisor, name, identifier, credits, new String(), new ArrayList<String>(), new ArrayList<Requisite>(), new String());

    }
    public boolean removeCourse(Advisor advisor, Course course){
        return true;
    }
    public boolean removeCourse(Advisor advisor, String name, int identifier){
        return true;
    }

    public boolean addGrade( Student student, Course course, Grade grade){
        if(user.getClass().toString().equals("Advisor")){
            return userList.addGrade(student, course, grade);
        }
        return false;
    } 
    public Student searchByStudentId(Advisor advisor, String id){
        return new Student(null, null, null, null, null);
    }
    public Student searchByStudentEmail(Advisor advisor, String email){
        return new Student(null, null, null, null, null);
    }
    public void addNotes(Student student, String notes){
        if(user.getClass().toString().equals("Advisor")){
            student.addNotes(notes);
        }
    }
    public void  addCourseForStudent(Advisor advisor, Student student, Course course){
        if(user.getClass().toString().equals("Advisor")){
            userList.addCourseForStudent(student, course);
        }
    }
    public void removeCourseForStudent(Advisor advisor, Student student, Course course){
        if(user.getClass().toString().equals("Advisor")){
            userList.removeCourseForStudent(student, course);
        }
    }
    public void enterFailureRisk(Student student, boolean failureRisk){
        if(user.getClass().toString().equals("Advisor")){
            student.editFailureRisk(failureRisk);
        }
    }
    public void removeFailureRisk(Advisor advisor, Student student, boolean failureRisk){
        if(user.getClass().toString().equals("Advisor")){
            student.editFailureRisk(failureRisk);
        }
    }
    public ArrayList<Student> getListOfAdvisses(Advisor advisor){
        return new ArrayList<Student>();
    }
    public Student getAdvisee(Advisor advisor, ArrayList<Student> students, String name){
        return new Student(null, null, null, null, null);
    }

    public String getEightSemesterPlan(Student student){
        return "";
    }

    public String  getAllCompletedCourses(Student student){
        return userList.getStudentCompleteCourses(student);
    }
    public String getAllUncompletedCourses(Student student){
        return userList.getStudentIncompleteCourses(student);
    }
    public boolean checkIfStudentAtRisk(Advisor advisor, Student student){
        return userList.checkIfStudentIsAtRisk(student);
    }

    public String getDegreeRequirements(String category){
        return "";
    }
    public String getDegreeCourses(String degree){
        return "";
    }

    public double getStudentOverallGPA(Student student) {
        return userList.studentOverallGPA(student);
    }
    public double  getStudentMajorGPA(Student student){
        return userList.studentMajorGPA(student);
    }
    public int calculateDegreeCompletionPercentage(Student student){
        return 0;
    }
    public String getCourseGrade(Student student, Course course){
        return userList.getCourseGrade(student, course);
    }
    public String getCourseGrade(Student student, String name, String identifier){
        return userList.getCourseGrade(student, name, identifier);
    }

}