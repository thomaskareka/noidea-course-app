import java.util.ArrayList;

public class DegreeTracker {
    private ArrayList<CourseProgress> studentCourses;
    //private Degree degree;
    private int completedCredits;

    public DegreeTracker(ArrayList<CourseProgress> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public double CalculateGPA() {
        return 0.0;
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

    public String createTranscipt(){
        String str = "";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + " - " + courseProgress.getCourseGrade() + "\n";
        }
        return str;
    }
}
