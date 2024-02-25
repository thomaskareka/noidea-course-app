import java.util.ArrayList;

public class CourseSystem{
    private UserList userList;
    private CourseList courseList;
    public User user;

    public CourseSystem(){
        
    }

    public User login (String email, String password){
        return user;
    }
    public boolean logout(){
        return true;
    }
    public boolean signUp(String firstName, String lastName, String email, String id, String password){
        return true;
    }
    public String courseDescriptionSearch(String name){
        return "";
    }
    public String courseDescriptionSearch(String subject, int identifier){
        return "";
    }
    public String courseRequistesSearch(String name){
        return "";
    }
    public String courseRequistesSearch(String subject, int identfier){
        return "";
    }
    public boolean addCourse(Advisor advisor, String name, String identifier, int credits, String description, ArrayList<String> attributes, Requisite requisite){
        return true;
    }
    public boolean addCourse(Advisor advisor, String name, String identifier, int credits){
        return true;
    }
    public boolean removeCourse(Advisor advisor, Course course){
        return true;
    }
    public boolean removeCourse(Advisor advisor, String name, int identifier){
        return true;
    }
    public boolean addGrade(Advisor advisor, Student student, Course course){
        return true;
    } 
    public Student searchByStudentId(Advisor advisor, String id){
        return new Student(null, null, null, null);
    }
    public Student searchByStudentEmail(Advisor advisor, String email){
        return new Student(null, null, null, null);
    }
    public void addNotes(Advisor advisor, Student student, String notes){
        
    }
    public void  addCourseForStudent(Advisor advisor, Student student, Course course){

    }
    public void moveCourse(Advisor advisor, Student student, Course course){

    }
    public void removeCourseForStudent(Advisor advisor, Student student, Course course){

    }
    public void enterFailureRisk(Advisor advisor, Student student, boolean failureRisk){

    }
    public void removeFailureRisk(Advisor advisor, Student student, boolean failureRisk){

    }
    public ArrayList<Student> getListOfAdvisses(Advisor advisor){
        return new ArrayList<Student>();
    }
    public Student getAdvisee(Advisor advisor, ArrayList<Student> students, String name){
        return new Student(null, null, null, null);
    }
    public String getEightSemesterPlan(Student student){
        return "";
    }
    public String  getAllCompletedCourses(Student student){
        return "";
    }
    public String getAllUncompletedCourses(Student student){
        return "";
    }
    public boolean checkIfStudentAtRisk(Advisor advisor, Student student){
        return true;
    }
    public String getDegreeRequirements(String category){
        return "";
    }
    public String getDegreeCourses(String degree){
        return "";
    }
    public double  getStudentOverallGPA(Student student){
        return 0.0;
    }
    public double  getStudentMajorGPA(Student student){
        return 0.0;
    }
    public int calculateDegreeCompletionPercentage(Student student){
        return 0;
    }
    public Grade getCourseGrade(Student student, Course course){
        return null;
    }
    public Grade getCourseGrade(Student student, String name, int identifier){
        return null;
    }

}