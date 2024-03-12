import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private String major;
    private String minor;
    private String applicationArea;
    private double majorGPA;
    private double overallGPA;
    private String classLevel;
    private UUID advisor;
    private boolean failureRisk;
    private ArrayList<String> notes;
    private boolean hasScholarship;
    private DegreeTracker degreeProgress;

    //new student constructor
    public Student(String firstName, String lastName, String email, String major, String password){
        super(firstName, lastName, email, password);
        this.major = major;
        this.degreeProgress = new DegreeTracker(new ArrayList<CourseProgress>());
    }

    //loading from JSON files constructor
    public Student(String firstName, String lastName, String email, UUID id, String major, String minor, double majorGPA, double overallGPA, String classLevel, UUID advisor, boolean failureRisk, ArrayList<String> notes, boolean hasScholarship, DegreeTracker degreeProgess, String password, String applicationArea){
        super(firstName, lastName, email, id, password);
        this.major = major; 
        this.minor = minor;
        this.majorGPA = majorGPA;
        this.overallGPA = overallGPA;
        this.classLevel = classLevel;
        this.advisor = advisor;
        this.failureRisk = failureRisk;
        this.notes = notes;
        this.hasScholarship = hasScholarship;
        this.applicationArea = applicationArea;
        this.degreeProgress = degreeProgess;
        
    }

    public String getTranscript(){
        return degreeProgress.createTranscipt();
    }

    public String getAllCourses(){
        return degreeProgress.getAllCourses();
    }

    /* 
    public String getEightSemesterPlan(){
        return "";
    }
    */

    public void editFailureRisk(boolean bool){
        failureRisk = bool;
    }

    public boolean checkIfAtRisk(){
        if(overallGPA < 3.00)
            failureRisk = true;
        else
            failureRisk = false;
        
        return failureRisk;
    }

    public String getAllCompletedCourses(){
        ArrayList<String> completedCourse = degreeProgress.GetCompleteCourses();
        String str = "";
        for (String string : completedCourse) {
            str += string + "\n";
        }
        return str;
    }

    public String getAllIncompletedCourses(){
        ArrayList<String> incompletedCourse = degreeProgress.GetIncompleteCourses();
        String str = "";
        for (String string : incompletedCourse) {
            str += string + "\n";
        }
        return str;
    }

    public void addCourse(Course course){
        degreeProgress.addCourse(course);
    }

    public void removeCourse(Course course){
        degreeProgress.removeCourse(course.getName());
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public double getMajorGPA() {
        return majorGPA;
    }

    public double getOverallGPA() {
        return overallGPA;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public UUID getAdvisorReference() {
        return advisor;
    }

    public void addNotes(String newNotes){
        notes.add(newNotes);
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public boolean hasScholarship() {
        return hasScholarship;
    }

    public String toString() {
        String out = super.toString();
        out += String.format("Major: %s (%f), Minor: %s, Application Area: %s\n", major, majorGPA, minor, applicationArea);
        out += "GPA: " + overallGPA + ", At risk: " + failureRisk + ", Has Scholarship: " + hasScholarship;
        out += "Advisor: " + advisor + "\n";
        out += "Notes: " + notes.toString();
        return out + "\n";
    }
}
