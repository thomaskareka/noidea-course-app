import java.util.ArrayList;

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
        //need to save user in JSON files
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

    public String showAllUserClasses(){ 
        return "";
    }

    //Degree Tracker for student is null
    public String createUserTranscript(String email){
        return userList.createUserTranscript(email);
    }

    public String courseDescriptionSearch(String name){
        return "";
    }
    public String courseDescriptionSearch( int identifier){
        return "";
    }
    public String courseRequistesSearch(String name){
        return "";
    }
    public String courseRequistesSearch( int identfier){
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
        return new Student(null, null, null, null, null);
    }
    public Student searchByStudentEmail(Advisor advisor, String email){
        return new Student(null, null, null, null, null);
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
        return new Student(null, null, null, null, null);
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