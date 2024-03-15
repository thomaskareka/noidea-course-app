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
    private String studentID;

    //new student constructor
    public Student(String firstName, String lastName, String email, String major, String password){
        super(firstName, lastName, email, password);
        this.major = major;
        this.degreeProgress = new DegreeTracker(new ArrayList<CourseProgress>());
        //prevent null references
        this.minor = "None";
        this.applicationArea = "None";
        this.majorGPA = 0;
        this.overallGPA = 0;
        this.classLevel = "Freshman";
        this.advisor = new UUID(0L, 0L);
        this.failureRisk = false;
        this.notes = new ArrayList<String>();
        this.hasScholarship = false;
        this.degreeProgress = new DegreeTracker(new ArrayList<CourseProgress>());
        this.studentID = "";
    }

    //loading from JSON files constructor
    public Student(String firstName, String lastName, String email, UUID id, String major, String minor, double majorGPA, double overallGPA, String classLevel, UUID advisor, boolean failureRisk, ArrayList<String> notes, boolean hasScholarship, DegreeTracker degreeProgess, String password, String applicationArea, String studentID){
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
        this.studentID = studentID;
        
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
    public void addCourse(String course){
        degreeProgress.addCourse(course);
    }
    public void removeCourse(String course){
        degreeProgress.removeCourse(course);
    }
    public String getCourseGrade(String name, String identifier){
       return degreeProgress.getCourseGrade(name, identifier);
    }

    public double getDegreePercentage(){
       return degreeProgress.CalculateProgress();
    }


    public boolean addGrade(String course, Grade grade){
        boolean out = degreeProgress.addGrade(course, grade);
        overallGPA = degreeProgress.CalculateGPA();
        majorGPA = degreeProgress.CalculateMajorGPA();
        return out;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if(DegreeList.getInstance().getMajor(major) != null) {
            this.major = major;
        } else {
            System.out.println("Major does not exist!");
        }
        
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

    public String getApplicationArea() {
        return applicationArea;
    }

    public String getStudentID() {
        return studentID;
    }

    public DegreeTracker getDegreeTracker() {
        return degreeProgress;
    }
    public String toString() {
        String out = super.toString() + "\n";
        out += String.format("Major: %s (%f), Minor: %s, Application Area: %s\n", major, majorGPA, minor, applicationArea);
        out += "GPA: " + overallGPA + ", At risk: " + failureRisk + ", Has Scholarship: " + hasScholarship + "\n";
        out += "Advisor: " + advisor + "\n";
        out += "Notes: " + notes.toString();
        return out + "\n";
    }
}
