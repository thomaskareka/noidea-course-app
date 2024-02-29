import java.util.ArrayList;

public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;

    private CourseList() {
        this.courses = new ArrayList<>();
    }

    public CourseList getInstance() {
        if (courseList == null ) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public Course getCourse(String subject, String identifier) {
        for( int i=0; i<courses.size(); i++ ){
            if(courses.get(i).getIdentifier().equals(identifier))
                return courses.get(i);
        }     
        return null;
    }

    public Course getCourse(String name) {
        for( int i=0; i<courses.size(); i++ ){
            if(courses.get(i).getName().equals(name))
                return courses.get(i);
        }  
        return null;
    }

    public ArrayList<String> getReqCourses(String category) {
        ArrayList<String> required = new ArrayList<>();
        DegreeRequirement degreeRequirement = new DegreeRequirement(category);
        required = degreeRequirement.getRequirements();
        return required;
    }

    //not sure what goes here
    public ArrayList<Course> getDegreeCourses(String degree) {
        return null;
    }
}
