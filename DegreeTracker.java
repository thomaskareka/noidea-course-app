import java.util.ArrayList;

public class DegreeTracker {
    private ArrayList<CourseProgress> studentCourses;
    //private Degree degree;
    private int completedCredits;

    public DegreeTracker(ArrayList<CourseProgress> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public double CalculateGPA() {
        double total = 0.0;
        double gpa = 0.0;
        for (CourseProgress courseProgress : studentCourses) {
            String grade = courseProgress.getCourseGrade();
            if(grade == "A") 
                total += 4.00;

            else if (grade == "B_PLUS")
                total += 3.5;
            
            else if (grade == "B") 
                total += 3.0;

            else if (grade == "C_PLUS")
                total += 2.5;
            
            else if (grade == "C")
                total += 2.0;

            else if (grade == "D_PLUS") 
                total += 1.5;

            else if (grade == "D")
                total += 1.0;

            else if (grade == "F") 
                total += 0.0;
        }
        
        gpa = total/studentCourses.size();
        
        return gpa;
    }

    public double CalculateMajorGPA() {
        return 0.0;
    }

    public double CalculateProgress() {
        return 0.0;
    }

    public ArrayList<Course> GetIncompleteCourses() {
        return null;
    }

    public ArrayList<Course> GetCompleteCourses() {
        return null;
    }

    public String generateEightSememsterPlan() {
        return " ";
    }

    public boolean checkIfDegreeRequirementsMet(Degree degree) {
        return true;
    }

    public int requirementCreditsCompleted(String category) {
        return 0;
    }

    public int requirmentCreditsRemaining(String category) {
        return 0;
    }

    public String getAllCourses(){
        String str = "";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + "\n";
        }
        return str;
    }

    public String createTranscipt(){
        String str = "";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + " - " + courseProgress.getCourseGrade() + "\n";
        }
        return str;
    }
}
