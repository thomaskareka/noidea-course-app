public class CourseProgress {
    private String course;
    private Grade gradeReceived;
    private String term;
    private boolean isComplete;


    public CourseProgress(String course, Grade gradeReceived, boolean isComplete) {
        this.course = course;
        this.gradeReceived = gradeReceived;
        this.isComplete = isComplete;
    }

    public Course getCourse(){
        return CourseList.getInstance().getCourseByIdentifer(course);
    }

    public String getCourseName(){
        Course courseObj = CourseList.getInstance().getCourseByIdentifer(course);
        String str = course + ": " + courseObj.getName();
        return str;
    }

    public String getCourseGrade(){
        String str = gradeReceived.toString();
        return str;
    }

    public CourseProgress(String course) {
        this.course = course;
    }

    public void makeCourseComplete() {
        isComplete = true;
    }

    public void makeCourseInProgress() {
        gradeReceived = Grade.IN_PROGRESS;
        isComplete = false;
    }

    public void makeCourseUncomplete() {
        isComplete = false;
    }

    public String toString() {
        return course + ": " + gradeReceived.toString();
    }
}
