public class CourseProgress {
    private Course course;
    private Grade gradeReceived;
    private String term;
    private boolean isComplete;
    private boolean isInProgress;

    public CourseProgress(Course course, Grade gradeReceived, boolean isComplete, boolean isInProgress) {
        this.course = course;
        this.gradeReceived = gradeReceived;
        this.isComplete = isComplete;
        this.isInProgress = isInProgress;
    }

    public Course getCourse(){
        return this.course;
    }

    public String getCourseName(){
        String str = course.getIdentifier() + ": " + course.getName();
        return str;
    }

    public String getCourseGrade(){
        String str = gradeReceived.toString();
        return str;
    }

    public CourseProgress(Course course) {
        this.course = course;
    }

    public void makeCourseComplete() {
        isComplete = true;
        isInProgress = false;
    }

    public void makeCourseInProgress() {
        isInProgress = true;
        isComplete = false;
    }

    public void makeCourseUncomplete() {
        isComplete = false;
    }

    
}
