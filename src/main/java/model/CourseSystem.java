package model;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseSystem{
    private static CourseSystem system;

    private UserList userList;
    private CourseList courseList;
    private DegreeList degreeList;
    private User user;
    private Student activeStudent;  // only for advisors
    private Advisor activeAdvisor;

    public CourseSystem() {
        userList = UserList.getInstance();
        courseList = CourseList.getInstance();
        degreeList = DegreeList.getInstance();
    }

    public static CourseSystem getInstance() {
        if(system == null) {
            system = new CourseSystem();
        }
        return system;
    }

    public void exit() {
        logout();
        DataWriter.saveAdvisors();
        DataWriter.saveStudents();
        DataWriter.saveCourses();
        DataWriter.saveMajors();
    }

    public boolean login (String email, String password){
       /* This method will call userList's login method and update the current user to be the person who logged in.
        If user is not null then the person successfully logged in */
        user = userList.login(email, password);
       if(user != null)
            return true;
       return false;
    }
    public void logout() {
        if(user == null) {
            return;
        }
        if(user instanceof Student) {
            userList.saveUser((Student) user);
        } else {
            userList.saveUser((Advisor) user);
            if(activeStudent != null) {
                userList.saveUser(activeStudent);
            }
        }
    }

    public void removeActiveStudent() {
        if(activeStudent == null) {
            return;
        }
        userList.saveUser(activeStudent);
        activeStudent = null;
    }
    public boolean signUp(boolean type, String firstName, String lastName, String email, String password){
        /* the boolean 'type' will be true if the user signing up as a student, and false if an advisor.
         All information that is not held in the User class will have to be inputted later in, maybe, an update profile method.*/
        user = userList.signUp(type, firstName, lastName, email, password);
        return (user != null);
    }
    public void setStudentID(String id) {
        if(user instanceof Student) {
            ((Student) user).setStudentID(id);
        } else {
            if(activeStudent != null) {
                activeStudent.setStudentID(id);
            } else {
                System.out.println("No chosen student!");
            }
        }
    }
    public String showCourseByCode(String identifier){
        Course courseInfo = courseList.getCourseByIdentifer(identifier);
        if(courseInfo == null)
            return null;
        return courseInfo.toString();
    } 
    public String showAllCourses(){
       return courseList.getAllCourses();
    }

    public String getCoursesWithAttribute(String attribute) {
        return courseList.getCoursesWithAttribute(attribute);
    }
    // has to be overwritten so advisor can get a student's classes
    public String getAllUserClasses(){ 
        return userList.getAllStudentCourses(user.getID());
    }

    public String createUserTranscript() {
        if(user instanceof Student) {
            return userList.createUserTranscript(user.getID());
        } else {
            if(activeStudent == null) {
                System.out.println("No student chosen!");
                return "";
            }
            return userList.createUserTranscript(activeStudent.getID());
        }
    }

    public ArrayList<DegreeRequirement> getCategoryRequirements(String category) {
        return getStudent().getCategoryRequirements(category);
    }
    
    public void printAllRequirements() {
        if(user instanceof Student) {
            ((Student) user).printAllRequirements();
        } else {
            if(activeStudent != null) {
                activeStudent.printAllRequirements();
            } else {
                System.out.println("No chosen student!");
            }
        }
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
    public boolean addGrade(String course, Grade grade){
        if(courseList.getCourseByIdentifer(course) == null) {
            System.out.println("Course doesn't exist: " + course);
            return false;
        }
        if(user instanceof Advisor){
            return userList.addGrade(activeStudent, course, grade);
        }
        return userList.addGrade((Student) user, course, grade);
    } 
    public boolean searchByStudentId(UUID id){
        if(user instanceof Advisor) {
            Advisor a = (Advisor) user;
            activeStudent = a.searchByStudentID(id);
            return (a != null);
        } else {
            System.out.println("Error: Invalid permissions (active user is a Student)");
            return false;
        }
    }

    public void addNote(String note){
        if(user instanceof Advisor) {
            if(user == null) {
                System.out.println("No student chosen!");
                return;
            }
            activeStudent.addNotes(note);
        } else {
            System.out.println("Only advisors can do this!");
        }
    }
    public boolean addCourseForStudent(String id) {
        Course course = courseList.getCourseByIdentifer(id);
        if(course == null) {
            System.out.println("Course not found.");
            return false;
        }
        System.out.println("Attempting to add course: " + id);
        if(user instanceof Advisor) {
            return userList.addCourseForStudent(activeStudent, id);
        } else {
            return userList.addCourseForStudent((Student) user, id);
        }
    }

    public void setStudentMajor(String major) {
        if(user instanceof Student) {
            Student s = (Student) user;
            s.setMajor(major);
        } else {
            activeStudent.setMajor(major);
        }
    }

    public void setStudentMinor(String minor) {
        if(user instanceof Student) {
            Student s = (Student) user;
            s.setMinor(minor);
        } else {
            activeStudent.setMinor(minor);
        }
    }

    public void setStudentApplicationArea(String appArea) {
        if(user instanceof Student) {
            Student s = (Student) user;
            s.setApplicationArea(appArea);
        } else {
            activeStudent.setApplicationArea(appArea);
        }
    }
    public boolean removeCourseForStudent(Course course){
        if(user instanceof Advisor) {
            return userList.removeCourseForStudent(activeStudent, course);
        }
        return false;
    }
    public void enterFailureRisk(Student student, boolean failureRisk){
        if(user instanceof Advisor) {
            student.editFailureRisk(failureRisk);
        }
    }
    public void removeFailureRisk(Advisor advisor, Student student, boolean failureRisk){
        if(user instanceof Advisor) {
            student.editFailureRisk(failureRisk);
        }
    }
    public ArrayList<Student> getListOfAdvisses(Advisor advisor){
        return new ArrayList<Student>();
    }

    public boolean addAdvisee(String id) {
        System.out.println("Attempting to add student with ID " + id);
        if (!(user instanceof Advisor)) {
            System.out.println("Only advisors can do this!");
            return false;
        } else {
            Student advisee = userList.getStudentFromStudentID(id);
            if(advisee == null) {
                System.out.println("Student not found!");
                return false;
            }
            ((Advisor) user).addAdvisee(advisee.getID());
            advisee.setAdvisor(user.getID());
            activeStudent = advisee;
            System.out.println(String.format("%s %s successfully added as %s %s's advisor!", user.getFirstName(), user.getLastName(), advisee.getFirstName(), advisee.getLastName()));
            return true;
        }
    }

    public boolean addAdvisee(Student advisee) {
        System.out.println("Attempting to add student with ID " + advisee.getID());
        if (!(user instanceof Advisor)) {
            System.out.println("Only advisors can do this!");
            return false;
        } else {
            if(((Advisor) user).addAdvisee(advisee.getID())) {
            advisee.setAdvisor(user.getID());
            activeStudent = advisee;
            System.out.println(String.format("%s %s successfully added as %s %s's advisor!", user.getFirstName(), user.getLastName(), advisee.getFirstName(), advisee.getLastName()));
            return true;
            }
            return false;
        }
    }

    public boolean chooseActiveStudent(String id) {
        if (!(user instanceof Advisor)) {
            System.out.println("Only advisors can do this!");
            return false;
        }
        Advisor a = (Advisor) user;
        for(UUID uuid : a.getStudents()) {
            Student s = (userList.getStudentFromID(uuid));
            if(s != null && s.getStudentID().equals(id)) {
                if(activeStudent != null) {
                    removeActiveStudent();
                }
                activeStudent = s;
                System.out.println(String.format("%s %s successfully loaded!", s.getFirstName(), s.getLastName()));
                return true;
            }
        }
        System.out.println("Student not found!");
        return false;
    }

    public boolean chooseActiveStudent(UUID id) {
        if (!(user instanceof Advisor)) {
            System.out.println("Only advisors can do this!");
            return false;
        }
        Advisor a = (Advisor) user;
        for(UUID uuid : a.getStudents()) {
            Student s = userList.getStudentFromID(id);
            if(uuid.equals(id) && s != null) {
                activeStudent = s;
                System.out.println(String.format("%s %s successfully loaded!", s.getFirstName(), s.getLastName()));
                return true;
            }
        }
        System.out.println("Student not found!");
        return false;
    }

    public String getEightSemesterPlan() {
        String out = "";
        String fileID = "";
        if(user instanceof Student) {
            out = ((Student) user).getMajorMap();
            fileID = user.getID().toString();
        } else {
            if(activeStudent != null) {
                out = activeStudent.getMajorMap();
                fileID = activeStudent.getID().toString();
            } else {
                System.out.println("No chosen student!");
                return "No chosen student!";
            }
        }
        //DataWriter.writeString(out, fileID);
        return out;
    }

    public ArrayList<String> getEightSemesterPlanList() {
        ArrayList<String> out = new ArrayList<>();
        if(getStudent() == null)
            return out;
        
        out = getStudent().getMajorMapList();
        return out;
    }

    public String getAllCompletedCourses(Student student){
        return userList.getStudentCompleteCourses(student);
    }
    public String getAllUncompletedCourses(Student student){
        return userList.getStudentIncompleteCourses(student);
    }
    public boolean checkIfStudentAtRisk(Student student){
        return userList.checkIfStudentIsAtRisk(student);
    }

    public String getDegreeRequirements(String category){
        return "";
    }
    public String getDegreeCourses(String degree){
        return "";
    }

    public String getAllApplicationAreas() {
        return degreeList.getAllApplicationAreas();
    }

    public double getStudentOverallGPA(Student student) {
        return userList.studentOverallGPA(student);
    }
    public double  getStudentMajorGPA(Student student){
        return userList.studentMajorGPA(student);
    }
    public double calculateDegreeCompletionPercentage(Student student){
        return userList.calculateDegreeCompletionPercentage(student);
    }
    public String getCourseGrade(Student student, Course course){
        return userList.getCourseGrade(student, course);
    }
    public String getCourseGrade(Student student, String identifier){
        return userList.getCourseGrade(student, identifier);
    }
    // debug functions
    public void printActiveUser() {
        System.out.println(user.toString());
    }

    public void printActiveStudent() {
        if(activeStudent != null) {
            System.out.println(activeStudent.toString());
        } else {
            System.out.println("No student selected!");
        }
    }

    public List<Course> getCoursesFromSearch(int page, String search) {
        return courseList.getCoursesFromSearch(page, search);
    }

    public List<Course> getStudentCoursesFromSearch(int page, String search) {
        return getStudent().getCoursesFromSearch(page, search);
    }

    public List<Student> getStudentsFromSearch(int page, String search) {
        return userList.getStudentsFromSearch(page, search);
    }

    public List<Student> getAdvisorsStudentsFromSearch(int page, String search) {
        if(user instanceof Advisor) {
            return ((Advisor) user).searchStudents(page, search);
        } else {
            return null;
        }
    }

    public String getUserType() {
        return user.getClass().toString();
    }

    public User getCurrentStudent() {
        return this.user;
    }

    public Student getStudent() {
        if (user instanceof Student) {
            return (Student) user;
        }
        return activeStudent;
    }

    public Advisor getAdvisor() {
        if (user instanceof Advisor) {
            return (Advisor) user;
        }
        return activeAdvisor;
    }

    public boolean isStudent() {
        return (user instanceof Student);
    }

    public boolean hasActiveStudent() {
        return activeStudent != null;
    }
}